package io.restzilla.service.impl;

import io.restzilla.service.CrudService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

/**
 * Registry for internal services.
 *
 * @author Jeroen van Schagen
 * @since Dec 10, 2015
 */
@SuppressWarnings("rawtypes")
class Services {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Services.class);

    private final ApplicationContext applicationContext;
    
    private Map<Class<?>, CrudService<?, ?>> instances;

    Services(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    /**
     * Retrieves the service by entity class.
     * 
     * @param entityClass the entity class
     * @return the retrieved entity class
     */
    CrudService<?, ?> getByEntityClass(Class<?> entityClass) {
        init(); // Lazy initialization
        return instances.get(entityClass);
    }

    private void init() {
        if (instances == null) {
            instances = new HashMap<Class<?>, CrudService<?, ?>>();
            LOGGER.debug("Scanning classpath for service beans...");
            for (CrudService<?, ?> service : getAllServices(applicationContext)) {
                LOGGER.debug("Registering service {} for {}...", service.getClass(), service.getEntityClass());
                instances.put(service.getEntityClass(), service);
            }
        }
    }

    private Collection<CrudService> getAllServices(ApplicationContext applicationContext) {
        Map<String, CrudService> services = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, CrudService.class);
        while (applicationContext.getParent() != null) {
            applicationContext = applicationContext.getParent();
            services.putAll(applicationContext.getBeansOfType(CrudService.class));
        }
        return services.values();
    }
    
}
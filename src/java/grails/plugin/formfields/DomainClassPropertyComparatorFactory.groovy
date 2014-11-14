package grails.plugin.formfields;

import org.codehaus.groovy.grails.commons.GrailsDomainClass;
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty;
import org.codehaus.groovy.grails.validation.ConstrainedProperty;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.Map;

/**
 * Given a domain class, provides a comparator for @GrailsDomainClassProperty instances.
 * @author deigote
 */
public abstract class DomainClassPropertyComparatorFactory {

    private Map<GrailsDomainClass, Comparator<GrailsDomainClassProperty>> comparatorsCache = [:]

    Comparator<GrailsDomainClassProperty> getComparator(GrailsDomainClass domainClass, Boolean ignoreCachedComparators = false) {
        Assert.notNull(domainClass, "Argument 'domainClass' is required!");
        Comparator<GrailsDomainClassProperty> currentDomainClassComparator =
           (ignoreCachedComparators ? null : comparatorsCache[domainClass]) ?:
              createComparatorForDomainClass(domainClass)
        if (!comparatorsCache.containsKey(domainClass)) {
            comparatorsCache[domainClass] = currentDomainClassComparator
        }
        return currentDomainClassComparator
    }

    protected abstract Comparator<GrailsDomainClassProperty> createComparatorForDomainClass(GrailsDomainClass domainClass)
}

package com.github.infovip.core.basic.jsp;

import com.github.infovip.core.basic.jsp.tags.DefaultModule;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author attila
 */
@Component
public class ModuleManager {

    /**
     * Modules are stored in this member
     */
    private final List<DefaultModule> modules;

    public ModuleManager() {
        this.modules = new LinkedList<>();
    }

    public void addModule(DefaultModule m) {
        modules.add(m);
    }

    public void removeModule(DefaultModule m) {
        modules.remove(m);
    }

    public String content() {
        StringBuilder sb = new StringBuilder();
        for (DefaultModule m : modules) {
            sb.append(m.getBodyContent().getString());
        }
        return sb.toString();
    }

    /**
     * Gets the module by its name
     *
     * @param name
     * @return
     */
    public DefaultModule getModuleByName(String name) {
        for (DefaultModule m : modules) {
            if (m.getModuleName().contentEquals(name)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Checks if the module is already exists
     *
     * @param moduleName
     * @return
     */
    public boolean existsModule(String moduleName) {
        for (DefaultModule m : modules) {
            if (m.getModuleName().contentEquals(moduleName)) {
                return true;
            }
        }
        return false;
    }

    public String content(String moduleName) {
        StringBuilder sb = new StringBuilder();
        for (DefaultModule m : modules) {
            if (m.getModuleName().contentEquals(moduleName)) {
                sb.append(m.getModuleContent());
            }
        }
        return sb.toString();
    }

    public List<DefaultModule> getModules() {
        return modules;
    }

}

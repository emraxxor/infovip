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
        System.out.println("Module has been added" + m.toString());
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
                sb.append(m.getBodyContent().getString());
            }
        }
        return sb.toString();
    }

    public List<DefaultModule> getModules() {
        return modules;
    }
    
}

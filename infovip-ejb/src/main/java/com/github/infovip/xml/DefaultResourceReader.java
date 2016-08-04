/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.xml;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * Reads the resources from the glassfish-resources.xml
 *
 * @author attila
 */
public class DefaultResourceReader {

    private static XMLConfiguration config = new XMLConfiguration();

    static {
        try {
            config.load(DefaultResourceReader.class.getClassLoader().getResourceAsStream("META-INF/glassfish-resources.xml"));
        } catch (ConfigurationException ex) {
            Logger.getLogger(DefaultResourceReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the value of the given option
     *
     * @param optionName 
     * @return
     */
    public static String getOption(String optionName) {
        List<HierarchicalConfiguration> property = config.configurationsAt("jdbc-connection-pool.property");
        for (HierarchicalConfiguration c : property) {
            if (c.getString("[@name]").contentEquals(optionName)) {
                return c.getString("[@value]");
            }
        }
        return null;
    }
}

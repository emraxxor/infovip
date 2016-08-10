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

/* global easejs */

/**
 * Represents a simple dojo component.
 * The components must be initialized in init method, and it is possible 
 * to use preinit functions before the init would be invoked. After the 
 * initialization it is possible to suer postinit if we would like to do 
 * something before the destroy method is called.
 * 
 * There is a method called requirements that must be implemented in the child class ,
 * since this determines which dependencies will be loaded by the AMD of dojo.
 * 
 * Typical place,display methods should be defined in display method.
 * 
 * @type DojoComponent
 */
var DojoComponent = easejs.AbstractClass('DojoComponent', {
    'public domReady': function () {
        var ob = this;
        var requirements = this.requirements();
        requirements.push("dojo/domReady!");
        require(requirements, function () {
            ob.preInit();
            ob.init();
            ob.postInit();
            ob.display();
            ob.destroy();
        });
    },
    'public virtual preInit': function () {},
    'public virtual postInit': function () {},
    'abstract public requirements': [],
    'abstract public init': [],
    'abstract public destroy': [],
    'abstract public display': []
});
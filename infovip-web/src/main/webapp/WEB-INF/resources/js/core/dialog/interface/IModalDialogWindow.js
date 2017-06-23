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
 * 
 * @author Attila Barna
 */

var IModalDialogWindow = easejs.Interface('IModalDialogWindow',{
	
	/**
	 * Displays the current window
	 */
    'public display' : ['options'],

    /**
     * The onReady method that is invoked when the component enters into ready state.
     * The callback method will be called, and the following paramaters must be passed to it :
     * the current window, and the data 
     */
    'public onReady' : ['callbackMethod','data'],
    
    
    /**
     * Hides the current window
     */
    'public hide' : [],
    
    /**
     * Disposes the current window
     */
    'public dispose' : [],
    
    /**
     * The default keylistener
     */
    'public keyListener' : [event],
    
    
    
});

/**
 * @author Attila Barna
 * 
 */

var Module = easejs.Class('Module',{

	//'private path' : ApplicationScope.config.JS_PATH,
	
	'private path' : 'js',
	
	'private callbacks' : null,
	
	'protected dependencies' : null,
	
    'virtual __construct': function () {
    	this.callbacks = new Array();
    	this.dependencies = new Array();
    },
    
    'public addCallback' : function(cb,params) {
    	this.callbacks.push({
    		c : cb,
    		p : params
    	});
    },
    
    'public dependency' : function(url) {
    		this.dependencies.push(url);
    },
    
    'public removeCallback' : function(v) {
        this.callbacks = this.callbacks.filter(o=> o !== v);

    },
    
    'public onBefore' : function(fn,args) {
    	this.addCallback(fn,args);
    	return this;
    },
    
    'public setPath' : function(v) {
    	this.path = v;
    	return this;
    },
    
    
    'public setName' : function(v) {
    	this.name = v;
    	return this;
    },
    
    'public load' : function() {
    	let loader = DependencyLoader.create();
    	this.dependencies.forEach(o=>loader.js(o));
    	loader.import( o => this.onComplete()  ,{});

    },
    
    'public virtual run' : function() {
    	throw new Error("Not implemented yet.");
     },
    
    'private onComplete' : function() {
    	for(let i=0; i<this.callbacks.length;++i) 
    		this.callbacks[i].c(this.callbacks[i].p);
    	
    	this.run();
     },
    
    'private url' : function() {
    	if ( ApplicationScope.config.MINIFIED ) 
    		return this.path + '/' + name + '.min.js'; 
    	
    	return this.path + '/' + name + '.js';
    }
    	
});


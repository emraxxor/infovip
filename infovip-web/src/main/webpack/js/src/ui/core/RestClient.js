/**
 * 
 * @author Attila Barna
 */
class RestClient {
 
    httpRequest(url, data, method,  options = { headers : { "Content-Type": "application/json"} } ) {
        if ( method == 'GET' ) 
            return axios.get(url, {params: data, headers: options.headers });
        
        return axios({method,url,data, options})
    }

    get(url, data)  {
        return this.httpRequest(url, data, 'GET');
    }
    
    post(url,data) {
        return this.httpRequest(url, data, 'POST');
    }
    
    put(url,data) {
        return this.httpRequest(url, data, 'PUT');
    }
    
    head(url,data) {
        return this.httpRequest(url, data, 'HEAD');
    }
    
    patch(url,data) {
        return this.httpRequest(url, data, 'PATCH');
    }
    
    request(url,data,type) {
        return this.httpRequest(url, data, type);
    }            
}

export default new RestClient();
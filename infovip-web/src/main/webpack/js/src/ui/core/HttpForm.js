/**
 * Generates a form, applicable to typical models. 
 * In the future, this component can only be used for clear forms, otherwise the use of "HttpClient" is recommended 
 * by default. 
 * 
 * This component is only created because the legacy javascript code is going to be deprecated, and will be removed,
 * so some old components are still supported by using this component. 
 * 
 * @author Attila Barna
 */
class HttpForm {
 
   createSearchParams(data) {
       let frm  = new URLSearchParams();
       if ( Array.isArray(data) ) {
           data.forEach( v => Object.keys(v).forEach(key => frm.append(key, v[key]) ));
       } else {
           Object.keys(data).forEach( key => frm.append(key,data[key]) ) ;  
       }
       return frm;
   }


   createFormData(data) {
        const frm = new FormData();
        if ( Array.isArray(data) ) {
            data.forEach( v => Object.keys(v).forEach(key => frm.append(key, v[key]) ));
        } else {
            Object.keys(data).forEach( key => frm.append(key,data[key]) ) ;  
        }
        return frm;
   }   

   post(url,data) {
       return axios.post(url, this.createSearchParams(data) )
   }
   
   get(url,data) {
       return axios.get(url, { params: data}); 
   }     
}

export default new HttpForm();
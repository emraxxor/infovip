import axios from 'axios';

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
}


/** 
 * Default Controller Class
 */
export default {

    created() {
    },

    methods: {

        get( url, data, callback = (e,a) => {} , args = {} , options = { method: 'GET' , headers : {} } ) {
            
            axios({
                method: options.method,
                url: url,
                data: this.params(data),
                headers : options.headers
            }).then((response) => callback(response,args),
                    (error) => console.log(error) );
        },

        post( url, data,  callback = (e,a) => {} , args = {} , options = {method : 'POST', headers : {}  }) {
            axios({
                method: options.method,
                url: url,
                data: this.params(data),
                headers : options.headers
            }).then((response) => callback(response,args),
                    (error) => console.log(error) );
        },

        json( url, data,  callback = (e,a) => {} , args = {} , options = {method : 'POST', headers : {
            "Content-Type": "application/json" 
        }  }) {
            axios({
                method: options.method,
                url: url,
                data: data,
                headers : options.headers
            }).then((response) => callback(response,args),
                    (error) => console.log(error) );
        },

        params(data) {
            let frm  = new URLSearchParams();
            if ( Array.isArray(data) ) {
                data.forEach( v => Object.keys(v).forEach(key => frm.append(key, v[key]) ));
            } else {
                Object.keys(data).forEach( key => frm.append(key,data[key]) ) ;  
            }
            return frm;
        },

        createFormData(data) {
            const frm = new FormData();
            if ( Array.isArray(data) ) {
                data.forEach( v => Object.keys(v).forEach(key => frm.append(key, v[key]) ));
            } else {
                Object.keys(data).forEach( key => frm.append(key,data[key]) ) ;  
            }
            return frm;
            
        }   
    }
}
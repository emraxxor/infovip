import axios from 'axios';
import restClient from './RestClient'
import httpForm from './HttpForm'

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
        
        httpClient() { 
            return restClient
        },

        httpForm() {
            return httpForm
        }        
    }
}
import Vuex from 'vuex'
import axios from 'axios'
import { resolve } from 'path';
import { reject } from 'lodash';

axios.defaults.headers.common = {
    'X-Requested-With': 'XMLHttpRequest',
}

export default new Vuex.Store({


    state : {
        likes : []
    },

    mutations : {
        setLikes : function(state, val) {
            state.likes = val 
        }
    },

    actions : {

        async addLike({commit}, { user , data }) {
        
            return new Promise( async (resolve,reject) => {    
                    try {
                        const res = await axios({
                                method: 'POST',
                                url: `/user/${user}/photo/like`,
                                data: data ,
                                headers : {
                                    "Content-Type": "application/json"
                                }
                        });
                        commit('setLikes', res.data ) 
                        
                        resolve(res.data)
                    } catch(err) {
                        reject(err)
                    }

            });
        },

        async likes({commit}, { data }) {
        
            return new Promise( async (resolve,reject) => {    
                    try {
                        const res = await axios({
                                method: 'POST',
                                url: `/gallery/likes`,
                                data: data ,
                                headers : {
                                    "Content-Type": "application/json"
                                }
                        });

                        commit('setLikes', res.data ) 
                        
                        resolve(res.data)
                    } catch(err) {
                        reject(err)
                    }

            });
        }

    },

    modules : {

    },

    getters : {
        getLikes : state => state.likes
    }
});  
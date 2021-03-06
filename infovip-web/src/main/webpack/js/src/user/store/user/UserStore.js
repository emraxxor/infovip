import httpClient from '@/ui/core/RestClient'

export default {
    
    namespaced: true,

    state : {
        user : {
            userName: ''
        }
    },

    mutations : {
        setUser : function(state, val) {
            state.user = val 
        }
    },

    actions : {
        fetchUser({commit}) {
            httpClient.get(`/api/user/current`).then( e => commit('setUser', e.data.data))
        }

    },


};  
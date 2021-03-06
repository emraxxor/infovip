import Vuex from 'vuex'
import httpClient from '@/ui/core/RestClient'
import httpForm from '@/ui/core/HttpForm'
import userStore from '../../../store/user/UserStore'
import { createStore } from 'vuex-extensions';

/**
 * 
 * @author Attila Barna
 */
export default createStore(Vuex.Store, {

   
    state : {
        activities: {
            count: 0,
            token: '',
            total: 0,
            data: []
        }
    },

    mutations : {

        setActivities : function(state, val) {
            state.activities = val 
        },

        incLike : function(state,val) {
            const elem = state.activities.data.find( e => e.documentId === val.documentId)
            elem.likes.push(val.req)
            elem.totalLikeCount++
        },


        incReplyLike : function(state,el) {
            const elem = state.activities.data.flatMap(e => e.comments).flatMap(e => e.replies).find(e => e.documentId === el.documentId )
            elem.totalLikeCount++ 
            elem.likes.push(el.resp)
        },

        decReplyLike : function(state,el) {
            const elem = state.activities.data.flatMap(e => e.comments).flatMap(e => e.replies).find(e => e.documentId === el.documentId )
            elem.totalLikeCount--
            elem.likes = elem.likes.filter( e => e.uid !== el.cuid)  
        },

        incCommentLike : function(state,el) {
            const activity = state.activities.data.find( e => e.documentId === el.parentDocument)
            const comment = activity.comments.find(e => e.documentId === el.documentId)
            if ( comment != null ) {
                comment.totalLikeCount++
                comment.likes.push(el.resp)
            }
        },

        decCommentLike : function(state,el) {
            const activity = state.activities.data.find( e => e.documentId === el.parentDocument)
            const comment = activity.comments.find(e => e.documentId === el.documentId)
            if ( comment != null ) {
                comment.totalLikeCount--
                comment.likes = comment.likes.filter( e => e.uid !== el.cuid)
            }
        },

        addReply: function(state, el) {
            const activity = state.activities.data.find( e => e.documentId === el.parentDocument)
            const comment = activity.comments.find(e => e.documentId === el.documentId)
            if ( comment != null ) 
                comment.replies.unshift(el.resp)
        },

        decLike : function(state,val) {
            const elem = state.activities.data.find( e => e.documentId === val.documentId)
            elem.likes = elem.likes.filter( e => e.uid !== val.cuid )
            elem.totalLikeCount--
        },
        
        addElement: function(state, el) {
            state.activities.data.unshift( 
                {
                    ...{likes:[],comments:[],totalLikeCount:0},
                    ...el.resp.data
                }
            )
        },

        addElements : function(state, elements) {
            state.activities.data = state.activities.data.concat(elements)
        },

        addComment: function(state, el) {
            state.activities.data
                 .find( e => e.documentId === el.parentDocument)
                 .comments.unshift(el)
        }

    },

    actions : {

        fetchInit: function({commit}) {
             httpClient.get(`/activity/data`)
            .then( e => commit('setActivities', e.data))
        },


        fetch: function({commit}, token ) {
            httpForm
            .get(`/activity/data`, { token })
            .then( e => commit('addElements', e.data.data ) )
        }

    },

    modules : {
        user : userStore
    },

    getters : {
        activities : state => state.activites
    },


});  
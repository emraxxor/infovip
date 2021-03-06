import Controller from "./Controller"

/**
 * @author Attila Barna
 */
export default {

    mixins: [Controller],

    computed: {
        currentUser() {
            return this.$store.state.user.user;
        },      
    },

    mounted() {
        this.$store.dispatch('user/fetchUser')
    }

}
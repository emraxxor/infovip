/**
 * Default date formatter
 * 
 * @author Attila Barna
 */
class DefaultDateFormatter {

    format(date) {
        return Vue.moment(date).format("YYYY-MM-DD HH:mm");
    }
}


export const DateFormatter = new DefaultDateFormatter()



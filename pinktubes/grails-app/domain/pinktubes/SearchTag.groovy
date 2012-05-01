package pinktubes

class SearchTag {

    String tag;
    static constraints = {
    }
    static mapping = {
       table 'search_tags_ii'
       id column:'id'
       columns {
         tag column:'search_tag'
       }
    }
}

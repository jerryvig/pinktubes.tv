package pinktubes

class VideoRecord {
    String videoURL;
    String title;
    String imgURL;
    String duration;
    Integer viewCount;
    String queryString;
    java.sql.Date recordDate;
    static constraints = {
    }
    static mapping = {
      table 'video_records'
      version false
      id column:'video_record_id'
      columns {
          videoURL column:'videoURL'
          title column:'title'
          imgURL column:'imgURL'
          duration column:'duration'
          viewCount column:'viewCount'
          queryString column:'query_string'
          recordDate column:'record_date'
      }
   }
}


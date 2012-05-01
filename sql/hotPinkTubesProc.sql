drop procedure hotPinkTubesProc;

delimiter //

CREATE PROCEDURE hotPinkTubesProc( timeFrame VARCHAR(32) ) 
BEGIN

DECLARE max_record_date, next_max_record_date DATE;

SET @@tmp_table_size=4294967295;
SET @@max_heap_table_size=4294967295;

SELECT MAX(record_date) INTO max_record_date FROM video_records;

DROP TEMPORARY TABLE IF EXISTS view_count_changes;

CREATE TEMPORARY TABLE view_count_changes ( videoURL VARCHAR(1024), viewCountChange INT ) ENGINE=MEMORY;

IF timeFrame='Today' THEN

  SELECT MAX(record_date) INTO next_max_record_date FROM video_records WHERE record_date!=max_record_date;

ELSEIF  timeFrame='Last 3 Days' THEN

  SELECT MAX(record_date) INTO next_max_record_date FROM video_records WHERE record_date<=DATE_SUB(max_record_date, INTERVAL 3 DAY );

ELSEIF  timeFrame='Last 5 Days' THEN

  SELECT MAX(record_date) INTO next_max_record_date FROM video_records WHERE record_date<=DATE_SUB(max_record_date, INTERVAL 5 DAY );

ELSEIF timeFRame='Last Week' THEN

  SELECT MAX(record_date) INTO next_max_record_date FROM video_records WHERE record_date<=DATE_SUB(max_record_date, INTERVAL 7 DAY );

ELSEIF timeFrame='Last Month' THEN

  SELECT MAX(record_date) INTO next_max_record_date FROM video_records WHERE record_date<=DATE_SUB(max_record_date, INTERVAL 28 DAY );

END IF;

INSERT INTO view_count_changes
  SELECT t1.videoURL, (t1.viewCount-t2.viewCount) AS viewCountChange FROM video_records AS t1, video_records AS t2 WHERE   (t2.videoURL=t1.videoURL AND t1.record_date=max_record_date AND t2.record_date=next_max_record_date);

DROP TABLE IF EXISTS view_count_changes_ii;

CREATE TEMPORARY TABLE view_count_changes_ii ( videoURL VARCHAR(1024), viewCountChange INT ) ENGINE=MEMORY;

INSERT INTO view_count_changes_ii SELECT videoURL, MAX(viewCountChange) AS Vchange FROM view_count_changes GROUP BY videoURL ORDER BY Vchange DESC;

DROP TABLE IF EXISTS view_count_changes;

END;



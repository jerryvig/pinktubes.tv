import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import groovy.sql.Sql;

class VideoRecord {
   String imgURL;
   String title;
   String videoURL;
   String duration;
   Integer viewCount;   
}

def searchXvideos( webClient, searchStr ) {
 
 splitSearchStr = searchStr.split(" ");
 searchStr = splitSearchStr[0];
 for ( i in 1..splitSearchStr.length ) {
   searchStr += "+" + splitSearchStr[i];
 } 
 println searchStr;

 webClient.setJavaScriptEnabled( false );
 def startPage = webClient.getPage( "http://www.xvideos.com/?k=" + searchStr );
 def docElement = startPage.getDocumentElement();
 def tdList = docElement.getHtmlElementsByTagName("td");
 
 hrefList = [];
 imgList = []
 descList = [];

 tdList.each {
    if ( (it.getWidthAttribute()).equals("183") ) {

      anchorList = it.getHtmlElementsByTagName("a");      
      for ( myAnchor in anchorList ) {
        if ( myAnchor.getHrefAttribute().startsWith("http://www.xvideos.com/video") ) {
           hrefList.add( myAnchor.getHrefAttribute().trim() );
        }
      }

      imgList = it.getHtmlElementsByTagName("img");
      for ( myImg in imgList ) {
        if ( myImg.getAttribute("class").equals("borderx") ) {
           imgList.add( myImg.getSrcAttribute() );
        }
      }

      spanList = it.getHtmlElementsByTagName("span");
      for ( mySpan in spanList ) {
        if ( mySpan.getAttribute("class").equals("red") ) {
           descList.add( mySpan.asText() );
        }
      }

    }
 }

 for ( i=0; i<hrefList.size(); i++ ) {
   println hrefList[i] + ", " + imgList[i] + ", " + descList[i];
 }

}

def searchPornhub( webClient, searchStr ) {

   splitSearchStr = searchStr.split(" ");
   searchStr = splitSearchStr[0];
   for ( def i=1; i<splitSearchStr.length; i++ ) {
     searchStr += "+" + splitSearchStr[i];
   }
   searchStr += "&x=0&y=0";
   println searchStr;

   webClient.setJavaScriptEnabled( false );
   def startPage = webClient.getPage( "http://www.pornhub.com/video/search?search=" + searchStr );
   def docElement = startPage.getDocumentElement();

   def videoList = [];

   def divList = docElement.getHtmlElementsByTagName("div");

   divList.each {

     def myVideoRecord = new VideoRecord();    

     if ( (it.getAttribute("class")).equals("wrap") ) {
        def imgList = it.getHtmlElementsByTagName("img");
        def firstImg = imgList.get( 0 );
        myVideoRecord.imgURL = firstImg.getSrcAttribute().trim();

        def h5List = it.getHtmlElementsByTagName("h5");
        for ( myH5 in h5List ) {
          if ( (myH5.getAttribute("class")).equals("title") ) {
             def h5Anchors = myH5.getHtmlElementsByTagName("a");
             def videoAnchor = h5Anchors.get( 0 );
             myVideoRecord.videoURL = videoAnchor.getHrefAttribute();
             myVideoRecord.title = (videoAnchor.getAttribute("title")).trim();    
          }  
        }

        def varList = it.getHtmlElementsByTagName("var");
        for ( myVar in varList ) {
          if ( myVar.getAttribute("class").equals("duration") ) {
            try {
              myVideoRecord.duration = (myVar.asText());
            }
            catch ( myE ) {
              println "duration error";
            }
          }
        }

        spanList = it.getHtmlElementsByTagName("span");
        for ( mySpan in spanList ) {
          if ( mySpan.getAttribute("class").equals("views") ) {
            try {
              myVideoRecord.viewCount = Integer.parseInt( mySpan.asText().replace(" views","").trim() );
            }
            catch ( myE ) {
               println "viewcount error";
            }
          }
        }

        videoList.add( myVideoRecord );

     }
   }

   return videoList;

}

def searchRedTube( webClient, searchStr ) {
   
  splitSearchStr = searchStr.split(" ");
  searchStr = splitSearchStr[0];
  for ( def i=1; i<splitSearchStr.length; i++ ) {
     searchStr += "+" + splitSearchStr[i];
  }
  println searchStr;

  webClient.setJavaScriptEnabled( false );
  def startPage = webClient.getPage( "http://www.redtube.com/?search=" + searchStr );
  def docElement = startPage.getDocumentElement();  

  def videoList = [];

  def liList = docElement.getHtmlElementsByTagName("li");
  for ( myLi in liList ) {
 
    def myVideoRecord = new VideoRecord();      

    divList = myLi.getHtmlElementsByTagName("div");
    for ( myDiv in divList ) {  
     if ( myDiv.getAttribute("class").equals("video") ) {

      def anchorList = myDiv.getHtmlElementsByTagName("a");
      for ( myAnchor in anchorList ) {
        if ( myAnchor.getHrefAttribute().startsWith("http://www.redtube.com/") ) {
          myVideoRecord.videoURL = myAnchor.getHrefAttribute();
        }
      }

      def imgList = myDiv.getHtmlElementsByTagName("img");
      def myImg = imgList.get( 0 );
      myVideoRecord.imgURL = myImg.getSrcAttribute();
      myVideoRecord.title = myImg.getAttribute("title");

     }
     if ( myDiv.getAttribute("class").equals("time") ) {
       spanList = myDiv.getHtmlElementsByTagName("span");
       def durationSpan = spanList.get( 0 );
       myVideoRecord.duration = durationSpan.asText();
     }
     if ( myDiv.getAttribute("class").equals("lastMovieRow") ) {
      nextDivList = myDiv.getHtmlElementsByTagName("div");
      myVideoRecord.viewCount = Integer.parseInt( nextDivList.get( 0 ).asText().replace(" views","").replace(",","").trim() );
     }
    }

    if ( myVideoRecord.videoURL != null ) {
      videoList.add( myVideoRecord ); 
    }

  }

  return videoList;

}

def searchYouPorn( webClient, searchStr ) {

  splitSearchStr = searchStr.split(" ");
  searchStr = splitSearchStr[0];
  for ( def i=1; i<splitSearchStr.length; i++ ) {
     searchStr += "+" + splitSearchStr[i];
  }
  searchStr += "&type=straight";
  println searchStr;

  webClient.setJavaScriptEnabled( false );
  def startPage = webClient.getPage( "http://youporn.com/" );
  def docElement = startPage.getDocumentElement();
  
  inputList = docElement.getHtmlElementsByTagName("input");
  inputList.each {
    if ( it.getAttribute("id").equals("enterbutton") ) {
       def nextPage = it.click();
    }
  }

  startPage = webClient.getPage( "http://youporn.com/search?query=" + searchStr + "&type=straight" );
  docElement = startPage.getDocumentElement();  

  def videoList = [];

  def liList = docElement.getHtmlElementsByTagName("li");
  for ( myLi in liList ) {

    def myVideoRecord = new VideoRecord();

    def h1List = myLi.getHtmlElementsByTagName("h1");
    if ( h1List.size() > 0 ) {
       myVideoRecord.title = h1List[0].asText();
       def anchorList = h1List[0].getHtmlElementsByTagName("a");
       myVideoRecord.videoURL = "http://youporn.com" + anchorList[0].getHrefAttribute();
    }
    
    def imgList = myLi.getHtmlElementsByTagName("img");
    if ( imgList.size() > 0 ) {
       myVideoRecord.imgURL = imgList[0].getSrcAttribute();
    }

    def divList = myLi.getHtmlElementsByTagName("div");
    for ( myDiv in divList ) {
      if ( myDiv.getAttribute("class").equals("duration_views") ) {
         def h2List = myDiv.getHtmlElementsByTagName("h2");
         myVideoRecord.duration = h2List[0].asText();
         def pList = myDiv.getHtmlElementsByTagName("p");
         myVideoRecord.viewCount = Integer.parseInt( pList[0].asText().replace(" views","").replace(",","").trim() );
      }
    }

    if ( myVideoRecord.videoURL != null ) {
       videoList.add( myVideoRecord );
    }

  }

  return videoList;

}

def searchTube8( webClient, searchStr ) {

  splitSearchStr = searchStr.split(" ");
  searchStr = splitSearchStr[0];
  for ( def i=1; i<splitSearchStr.length; i++ ) {
     searchStr += "+" + splitSearchStr[i];
  }
  println searchStr;

  webClient.setJavaScriptEnabled( false );
  def startPage = webClient.getPage( "http://www.tube8.com/search.html?q=" + searchStr );
  def docElement = startPage.getDocumentElement();  

  def videoList = []; 

  def divList = docElement.getHtmlElementsByTagName("div");
  for ( myDiv in divList ) {
    if ( myDiv.getAttribute("class").equals("thumb-wrapper") ) {

       def myVideoRecord = new VideoRecord();

       def nextDivList = myDiv.getHtmlElementsByTagName("div");
       for ( myNextDiv in nextDivList ) {

         if ( myNextDiv.getAttribute("id").startsWith("video_") ) {
            def h2List = myNextDiv.getHtmlElementsByTagName("h2");
            def anchorList = h2List[0].getHtmlElementsByTagName("a");
            myVideoRecord.videoURL = anchorList[0].getHrefAttribute();
            myVideoRecord.title = anchorList[0].asText();
            def thirdDivList = myNextDiv.getHtmlElementsByTagName("div");
            for ( myThirdDiv in thirdDivList ) {
               if ( myThirdDiv.getAttribute("class").equals("video-right-text float-right") ) {
                  myVideoRecord.duration = myThirdDiv.asText();
               } 
            }
         }

         if ( myNextDiv.getAttribute("class").equals("video-cont-wrapper") ) {
           def thirdDivList = myNextDiv.getHtmlElementsByTagName("div");
           if ( thirdDivList[0].asText().endsWith("views") ) {
              myVideoRecord.viewCount = Integer.parseInt( thirdDivList[0].asText().replace(" views","").trim() );
           }
         }
        
       }
       videoList.add( myVideoRecord );
    }
  }

  return videoList;

}

def searchXHamster( webClient, searchStr ) {

  splitSearchStr = searchStr.split(" ");
  searchStr = splitSearchStr[0];
  for ( def i=1; i<splitSearchStr.length; i++ ) {
     searchStr += "+" + splitSearchStr[i];
  }
  searchStr += "&qcat=video";
  println searchStr;

  webClient.setJavaScriptEnabled( false );
  def startPage = webClient.getPage( "http://xhamster.com/search.php?q=" + searchStr + "&qcat=video" );
  def docElement = startPage.getDocumentElement();  

  def videoList = [];

  def tdList = docElement.getHtmlElementsByTagName("td");
  for ( myTd in tdList ) {
    if ( myTd.getAttribute("align").equals("center") && myTd.getAttribute("width").equals("166px") && myTd.getAttribute("valign").equals("top") ) {

    def myVideoRecord = new VideoRecord();

    def aList = myTd.getHtmlElementsByTagName("a");
    for ( myAnchor in aList ) {
      if ( myAnchor.getHrefAttribute().startsWith("/movies/") && myAnchor.getAttribute("class").equals("hRotator") ) {
         def imgList = myAnchor.getHtmlElementsByTagName("img");
         myVideoRecord.imgURL = imgList[0].getSrcAttribute();
      }
    }

    def divList = myTd.getHtmlElementsByTagName("div");
    for ( myDiv in divList ) { 

      if ( myDiv.getAttribute("class").equals("moduleFeaturedTitle") ) {
        def anchorList = myDiv.getHtmlElementsByTagName("a");
        myVideoRecord.videoURL = "http://xhamster.com" + anchorList[0].getHrefAttribute();
        myVideoRecord.title = anchorList[0].asText();
      }

      if ( myDiv.getAttribute("class").equals("moduleFeaturedDetails") ) {
        myVideoRecord.viewCount = Integer.parseInt( (myDiv.asText().split("\n")[1]).split(":")[1].replace(",","").trim() );
        myVideoRecord.duration = (myDiv.asText().split("\n")[0]).split(":")[1].replace("m",":").replace("s","").trim();
      }

    }

    if ( myVideoRecord.videoURL != null ) {
      videoList.add( myVideoRecord );
    }

  }
 }

 return videoList; 

}

def runPinkTubes( def queryString, def sqlConnection ) {

  def myWC = new WebClient( BrowserVersion.FIREFOX_3 );

  theVideoList = [];

  //searchXvideos( myWC, searchString );

  theVideoList.add( searchPornhub( myWC, queryString ) );
  theVideoList.add( searchXHamster( myWC, queryString ) );
  theVideoList.add( searchYouPorn( myWC, queryString ) );
  theVideoList.add( searchTube8( myWC, queryString ) );
  theVideoList.add( searchRedTube( myWC, queryString ) );

  def today = new java.sql.Date( (new java.util.Date()).getTime() );

  theVideoList.each {
    for ( myVideoRecord in it ) {
      //println myVideoRecord.videoURL;
      sqlConnection.execute( "INSERT INTO video_records ( videoURL, title, imgURL, duration, viewCount, query_string, record_date ) VALUES ( '" + myVideoRecord.videoURL + "', '" + myVideoRecord.title.replaceAll("'","") + "', '" + myVideoRecord.imgURL + "', '" + myVideoRecord.duration + "', " + myVideoRecord.viewCount + ", '" + queryString + "', '" + today.toString() + "' )" );     
    }
  }

}

def sql = Sql.newInstance("jdbc:mysql://localhost/pinktubes?user=root");

sql.eachRow("SELECT DISTINCT search_tag FROM search_tags ORDER BY search_tag ASC") {
  println "${it.search_tag}";
  runPinkTubes( "${it.search_tag}", sql );
}

sql.eachRow("SELECT CONCAT(t1.search_tag,' ',t2.search_tag) AS search_tag FROM search_tags AS t1, search_tags AS t2 WHERE ( t2.search_tag>t1.search_tag ) ORDER BY t1.search_tag ASC, t2.search_tag ASC") {
   println "${it.search_tag}";
   runPinkTubes( "${it.search_tag}", sql ); 
}



/*
sql.eachRow("SELECT * FROM video_records ORDER BY viewCount DESC") {
   println "${it.videoURL}, ${it.viewCount}";
} */

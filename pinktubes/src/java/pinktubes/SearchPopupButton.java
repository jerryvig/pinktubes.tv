package pinktubes;

import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class SearchPopupButton extends Button {
    String videoURL;
    String videoTitle;
    Window mainWindow;
    String duration;
    String viewCount;
   
    String selectedStyle = "font-weight:bold;background-color:#FF0080;";
    String unSelectedStyle = "font-weight:bold;background-color:#FF69B4;";
    String hoverStyle = "font-weight:bold;background-color:#FC0FC0;";
    String selectedHoverStyle = "font-weight:bold;background-color:#000000;color:#FF0080;";

    SearchPopupButton( String _videoURL, String _videoTitle, Window _mainWindow, String _imgURL, String _duration, String _viewCount ) {
        videoURL = _videoURL;
        videoTitle = _videoTitle;
        mainWindow = _mainWindow;
        duration = _duration;
        viewCount = _viewCount;
	setImage( _imgURL );
	setOrient("vertical");
	setLabel( _videoTitle + ", " + duration + ", " + viewCount + " views" );
	setStyle( unSelectedStyle );
        setWidth("200px");
        // setHeight("180px");
    }

    public void onClick() {
	setStyle( selectedStyle );
        VideoPopWindow popWindow = new VideoPopWindow( videoURL, videoTitle, this );
	mainWindow.appendChild( popWindow );
    }

    public void onMouseOver () {
       if ( getStyle().equals( unSelectedStyle ) ) {
	  setStyle( hoverStyle );
       }
       if ( getStyle().equals( selectedStyle ) ) {
	  setStyle( selectedHoverStyle );
       }      
    }
 
    public void onMouseOut () {
       if ( getStyle().equals( hoverStyle ) ) {
	  setStyle( unSelectedStyle );
       } 
       if ( getStyle().equals( selectedHoverStyle ) ) {
	  setStyle( selectedStyle );
       }
    }
    
}
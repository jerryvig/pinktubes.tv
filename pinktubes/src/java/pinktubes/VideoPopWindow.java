package pinktubes;

import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class VideoPopWindow extends Window {
    String videoURL;   
    String videoTitle;
    SearchPopupButton searchPopupButton;

    VideoPopWindow( String _videoURL, String _videoTitle, SearchPopupButton _searchPopupButton ) {
       videoURL = _videoURL;
       videoTitle = _videoTitle;
       searchPopupButton = _searchPopupButton;
       setWidth("800px");
       setHeight("800px");
       setBorder("normal");
       setClosable(true);
       setMaximizable(true);
       setSizable(true);
       setPosition("center");
       setTitle( _videoTitle );
       Iframe popupFrame = new Iframe( _videoURL );
       popupFrame.setWidth("100%");
       popupFrame.setHeight("100%"); 
       appendChild( popupFrame );
       doOverlapped();
    }

    public void onClose() {
       detach();
       searchPopupButton.setStyle( searchPopupButton.unSelectedStyle );  
    }
    
}
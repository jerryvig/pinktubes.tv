package pinktubes

import org.zkoss.zkgrails.*
import org.zkoss.zhtml.*;

class PinkTubeSiteComposer extends GrailsComposer {

    def pinkTubeSiteGridRows;

    def afterCompose = { window ->
        // initialize components here
        redraw();
    }
    
    def redraw(page=0) {
       def list = PinkTubeSite.list(sort: 'alexaRank');
       
       pinkTubeSiteGridRows.append {
         list.each { e ->
           row(value: e) {
              label(value: e.name)
              a(href: e.url, label: e.url, target: "top")
              label(value: e.domain)
              label(value: e.alexaRank)
              label(value: e.alexaUSRank)
           }
         }
       }
    }

}

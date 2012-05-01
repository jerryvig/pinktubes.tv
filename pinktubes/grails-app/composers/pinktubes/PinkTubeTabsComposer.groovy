package pinktubes;

import java.util.ArrayList;
import org.zkoss.zkgrails.*;
import org.zkoss.zk.ui.Component;
import groovy.sql.Sql;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;
import org.zkoss.zul.Button;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zhtml.Br;

class PinkTubeTabsComposer extends GrailsComposer {

    Div searchDiv;
    Div searchResultsDiv;
    Window mainWindow;
    ArrayList<Button> selectedButtonList = new ArrayList<Button>();
    Div hotPinkHomeDiv;
    Div hotPinkResultsDiv;
    Tabs pinkTubeSiteTabs;
    Tabpanels pinkTubeSiteTabpanels;

    Sql sql = Sql.newInstance("jdbc:mysql://localhost/pinktubes?user=root")

    def afterCompose = { window ->
        // initialize components here
        redraw();
    }

    def redraw() {
       def searchTagList = SearchTag.list()
       
       for ( def i=0; i<searchTagList.size(); i++ ) {
          searchDiv.append {           
            SearchTagButton tagButton = new SearchTagButton( searchTagList[i], selectedButtonList, searchResultsDiv, sql.getConnection(), mainWindow );            
            searchDiv.appendChild( tagButton );
	  }
       }
     
      searchResultsDiv.append {
         div (align:"center") {
           label(value:"Search results will appear here.", style:"font-weight:bold;font-size:16pt;color:#FFC0CB;");
         }
      }      

      hotPinkHomeDiv.append {   
        label(value:"Most Popular PinkTubes", style:"font-weight:bold;font-size:18pt;align:center;color:#FFC0CB;");
      }
      hotPinkHomeDiv.appendChild( new Br() );
      def selectedHotPinkButton = [ new Button() ];
      def hotPinkButtonLabels = ["Today", "Last 3 Days", "Last 5 Days", "Last Week", "Last Month"];
      hotPinkButtonLabels.each {
         hotPinkHomeDiv.appendChild( new HotPinkButton( it, selectedHotPinkButton, sql, hotPinkResultsDiv, mainWindow) ); 
      }
     
      sql.eachRow("SELECT name FROM pink_tube_sites ORDER BY alexa_rank ASC LIMIT 10") { row ->
        pinkTubeSiteTabs.append {
	  tab(label:"${row.name}")
        }
        pinkTubeSiteTabpanels.append {
          tabpanel() {
            label(value:"${row.name}")
          }
        }
      }


    }

}

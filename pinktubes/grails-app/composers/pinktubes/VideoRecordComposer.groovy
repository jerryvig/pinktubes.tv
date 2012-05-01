package pinktubes

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.event.*

class VideoRecordComposer extends GrailsComposer {

    def listVideoRecord
    def pagVideoRecord

    def afterCompose = { window ->
        // initialize components here
        pagVideoRecord.totalSize = VideoRecord.count()
        redraw()
    }

    def onPaging_pagVideoRecord(ForwardEvent fe) {
       def e = fe.origin
       redraw(e.activePage)
    }

    def redraw(page=0) {
       def list = VideoRecord.list( offset: page * pagVideoRecord.pageSize, max: pagVideoRecord.pageSize );
       listVideoRecord.clear()
       listVideoRecord.append {
         list.each { e ->
            listitem( value: e ) {
             listcell( label: e.videoURL )
             listcell( label: e.title )
             listcell( label: e.viewCount )
             listcell( label: e.duration )
            }
         }
       }
     }

}

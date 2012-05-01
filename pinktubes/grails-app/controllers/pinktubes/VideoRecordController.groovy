package pinktubes

class VideoRecordController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [videoRecordInstanceList: VideoRecord.list(params), videoRecordInstanceTotal: VideoRecord.count()]
    }

    def create = {
        def videoRecordInstance = new VideoRecord()
        videoRecordInstance.properties = params
        return [videoRecordInstance: videoRecordInstance]
    }

    def save = {
        def videoRecordInstance = new VideoRecord(params)
        if (videoRecordInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), videoRecordInstance.id])}"
            redirect(action: "show", id: videoRecordInstance.id)
        }
        else {
            render(view: "create", model: [videoRecordInstance: videoRecordInstance])
        }
    }

    def show = {
        def videoRecordInstance = VideoRecord.get(params.id)
        if (!videoRecordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
            redirect(action: "list")
        }
        else {
            [videoRecordInstance: videoRecordInstance]
        }
    }

    def edit = {
        def videoRecordInstance = VideoRecord.get(params.id)
        if (!videoRecordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [videoRecordInstance: videoRecordInstance]
        }
    }

    def update = {
        def videoRecordInstance = VideoRecord.get(params.id)
        if (videoRecordInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (videoRecordInstance.version > version) {
                    
                    videoRecordInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'videoRecord.label', default: 'VideoRecord')] as Object[], "Another user has updated this VideoRecord while you were editing")
                    render(view: "edit", model: [videoRecordInstance: videoRecordInstance])
                    return
                }
            }
            videoRecordInstance.properties = params
            if (!videoRecordInstance.hasErrors() && videoRecordInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), videoRecordInstance.id])}"
                redirect(action: "show", id: videoRecordInstance.id)
            }
            else {
                render(view: "edit", model: [videoRecordInstance: videoRecordInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def videoRecordInstance = VideoRecord.get(params.id)
        if (videoRecordInstance) {
            try {
                videoRecordInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'videoRecord.label', default: 'VideoRecord'), params.id])}"
            redirect(action: "list")
        }
    }
}

package pinktubes

class PinkTubeSiteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pinkTubeSiteInstanceList: PinkTubeSite.list(params), pinkTubeSiteInstanceTotal: PinkTubeSite.count()]
    }

    def create = {
        def pinkTubeSiteInstance = new PinkTubeSite()
        pinkTubeSiteInstance.properties = params
        return [pinkTubeSiteInstance: pinkTubeSiteInstance]
    }

    def save = {
        def pinkTubeSiteInstance = new PinkTubeSite(params)
        if (pinkTubeSiteInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), pinkTubeSiteInstance.id])}"
            redirect(action: "show", id: pinkTubeSiteInstance.id)
        }
        else {
            render(view: "create", model: [pinkTubeSiteInstance: pinkTubeSiteInstance])
        }
    }

    def show = {
        def pinkTubeSiteInstance = PinkTubeSite.get(params.id)
        if (!pinkTubeSiteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pinkTubeSiteInstance: pinkTubeSiteInstance]
        }
    }

    def edit = {
        def pinkTubeSiteInstance = PinkTubeSite.get(params.id)
        if (!pinkTubeSiteInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [pinkTubeSiteInstance: pinkTubeSiteInstance]
        }
    }

    def update = {
        def pinkTubeSiteInstance = PinkTubeSite.get(params.id)
        if (pinkTubeSiteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pinkTubeSiteInstance.version > version) {
                    
                    pinkTubeSiteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite')] as Object[], "Another user has updated this PinkTubeSite while you were editing")
                    render(view: "edit", model: [pinkTubeSiteInstance: pinkTubeSiteInstance])
                    return
                }
            }
            pinkTubeSiteInstance.properties = params
            if (!pinkTubeSiteInstance.hasErrors() && pinkTubeSiteInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), pinkTubeSiteInstance.id])}"
                redirect(action: "show", id: pinkTubeSiteInstance.id)
            }
            else {
                render(view: "edit", model: [pinkTubeSiteInstance: pinkTubeSiteInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pinkTubeSiteInstance = PinkTubeSite.get(params.id)
        if (pinkTubeSiteInstance) {
            try {
                pinkTubeSiteInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'pinkTubeSite.label', default: 'PinkTubeSite'), params.id])}"
            redirect(action: "list")
        }
    }
}

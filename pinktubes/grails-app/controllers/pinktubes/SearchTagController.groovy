package pinktubes

class SearchTagController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [searchTagInstanceList: SearchTag.list(params), searchTagInstanceTotal: SearchTag.count()]
    }

    def create = {
        def searchTagInstance = new SearchTag()
        searchTagInstance.properties = params
        return [searchTagInstance: searchTagInstance]
    }

    def save = {
        def searchTagInstance = new SearchTag(params)
        if (searchTagInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), searchTagInstance.id])}"
            redirect(action: "show", id: searchTagInstance.id)
        }
        else {
            render(view: "create", model: [searchTagInstance: searchTagInstance])
        }
    }

    def show = {
        def searchTagInstance = SearchTag.get(params.id)
        if (!searchTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            [searchTagInstance: searchTagInstance]
        }
    }

    def edit = {
        def searchTagInstance = SearchTag.get(params.id)
        if (!searchTagInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [searchTagInstance: searchTagInstance]
        }
    }

    def update = {
        def searchTagInstance = SearchTag.get(params.id)
        if (searchTagInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (searchTagInstance.version > version) {
                    
                    searchTagInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'searchTag.label', default: 'SearchTag')] as Object[], "Another user has updated this SearchTag while you were editing")
                    render(view: "edit", model: [searchTagInstance: searchTagInstance])
                    return
                }
            }
            searchTagInstance.properties = params
            if (!searchTagInstance.hasErrors() && searchTagInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), searchTagInstance.id])}"
                redirect(action: "show", id: searchTagInstance.id)
            }
            else {
                render(view: "edit", model: [searchTagInstance: searchTagInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def searchTagInstance = SearchTag.get(params.id)
        if (searchTagInstance) {
            try {
                searchTagInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'searchTag.label', default: 'SearchTag'), params.id])}"
            redirect(action: "list")
        }
    }
}

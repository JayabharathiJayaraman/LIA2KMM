import shared

class IOSFormViewModel {
    static let shared = IOSFormViewModel()
    
    var formViewModel = FormViewModel(form: FormFactory().createForm(type: FormType.generalquestions))
    
    func changeForm(toType type: FormType) {
        switch (type) {
        case FormType.generalquestions:
            formViewModel = FormViewModel(form: FormFactory().createForm(type: FormType.generalquestions))
        case FormType.soilstructure:
            formViewModel = FormViewModel(form: FormFactory().createForm(type: FormType.soilstructure))
        case FormType.infiltration:
            formViewModel = FormViewModel(form: FormFactory().createForm(type: FormType.infiltration))

        default:
            return
        }
    }
}

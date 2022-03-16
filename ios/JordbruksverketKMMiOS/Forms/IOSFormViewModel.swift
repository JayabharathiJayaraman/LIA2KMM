import shared

class IOSFormViewModel {
    static let shared = FormViewModel(form: FormFactory().createForm())
}

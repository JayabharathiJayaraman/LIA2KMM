import shared
import UIKit

final class InformationViewController: UIViewController {
    private let components: [FormComponent]
    private let formGenerator: IOSFormGenerator

    @IBOutlet private var contentView: UIView!

    init(components: [FormComponent], formGenerator: IOSFormGenerator = .init()) {
        self.components = components
        self.formGenerator = formGenerator

        super.init(nibName: String(describing: InformationViewController.self), bundle: nil)
    }

    required init?(coder: NSCoder) { nil }

    override func viewDidLoad() {
        super.viewDidLoad()

        renderComponents()
    }
}

private extension InformationViewController {
    func renderComponents() {
        guard let stackView = formGenerator.createInterface(components: components, currentScreen: 99) as? UIStackView else { return }

        contentView.addSubview(stackView)
        stackView.translatesAutoresizingMaskIntoConstraints = false
        let constraints = [
            stackView.topAnchor.constraint(equalTo: contentView.topAnchor),
            stackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            stackView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
            stackView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor)
        ]
        NSLayoutConstraint.activate(constraints)
    }
}

import UIKit

final class ModalViewController: UIViewController {
    typealias CloseButtonTapHandler = () -> Void

    var closeButtonTapHandler: CloseButtonTapHandler?

    private let contentViewController: UIViewController

    @IBOutlet private var contentView: UIView!
    @IBOutlet private var closeButton: UIButton!

    init(contentViewController: UIViewController) {
        self.contentViewController = contentViewController
        super.init(nibName: String(describing: ModalViewController.self), bundle: nil)
    }

    required init?(coder: NSCoder) { nil }

    override func viewDidLoad() {
        super.viewDidLoad()

        contentView.addSubview(contentViewController.view)
        contentViewController.view.translatesAutoresizingMaskIntoConstraints = false
        let constraints = [
            contentViewController.view.topAnchor.constraint(equalTo: contentView.topAnchor),
            contentViewController.view.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            contentViewController.view.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
            contentViewController.view.leadingAnchor.constraint(equalTo: contentView.leadingAnchor)
        ]
        NSLayoutConstraint.activate(constraints)
        addChild(contentViewController)
        contentViewController.didMove(toParent: self)
    }

    @IBAction private func handleCloseButtonTap(_: UIButton) {
        closeButtonTapHandler?()
    }
}

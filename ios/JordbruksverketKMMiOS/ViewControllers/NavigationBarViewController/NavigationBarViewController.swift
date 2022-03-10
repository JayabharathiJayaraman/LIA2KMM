import UIKit

typealias ShowViewControllerHandler = (UIViewController) -> Void

class NavigationBarViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        additionalSafeAreaInsets = UIEdgeInsets(top: 80, left: 0, bottom: 0, right: 0)
        addNavigationBar()
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .darkContent
    }

    private func addNavigationBar() {
        guard let navigationBar = UIView.fromNib(String(describing: NavigationBar.self)) as? NavigationBar else { return }
        navigationBar.backAction = {
            self.dismiss(animated: true)
        }
        view.addSubview(navigationBar)

        let guide = view.safeAreaLayoutGuide
        navigationBar.translatesAutoresizingMaskIntoConstraints = false
        navigationBar.trailingAnchor.constraint(equalTo: guide.trailingAnchor).isActive = true
        navigationBar.leadingAnchor.constraint(equalTo: guide.leadingAnchor).isActive = true
        navigationBar.topAnchor.constraint(equalTo: guide.topAnchor, constant: -85).isActive = true
        navigationBar.heightAnchor.constraint(equalToConstant: LocalConstants.navigationBarHeight).isActive = true
    }
}

private extension NavigationBarViewController {
    struct LocalConstants {
        static let navigationBarHeight: CGFloat = 80
    }
}

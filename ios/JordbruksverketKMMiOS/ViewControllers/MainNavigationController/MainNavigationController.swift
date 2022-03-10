import UIKit

final class MainNavigationController: UINavigationController {
    override func viewDidLoad() {
        super.viewDidLoad()

        interactivePopGestureRecognizer?.isEnabled = false
        isNavigationBarHidden = false

        removeBottomLine()
    }
}

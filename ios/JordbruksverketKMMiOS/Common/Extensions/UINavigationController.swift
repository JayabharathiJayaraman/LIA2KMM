import UIKit

extension UINavigationController {
    func removeBottomLine() {
        navigationBar.shadowImage = UIImage()
        navigationBar.setBackgroundImage(UIImage(), for: .default)
    }

    func setBackIndicatorImage(_ image: UIImage, withTintColor tintColor: UIColor = .black) {
        navigationBar.tintColor = tintColor
        navigationBar.backIndicatorImage = image
        navigationBar.backIndicatorTransitionMaskImage = image
    }
}

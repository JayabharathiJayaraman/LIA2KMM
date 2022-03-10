import UIKit

typealias BackAction = (() -> Void)

final class NavigationBar: UIView {
    var backAction: BackAction?
    
    @IBAction func backButtonTapped(_ sender: Any) {
        backAction?()
    }
}

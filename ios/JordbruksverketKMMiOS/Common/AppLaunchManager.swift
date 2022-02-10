import UIKit

protocol AppLaunchManager {
    var rootViewController: UIViewController { get }
    //var dataPrivacyViewController: UIViewController { get }
}

struct DefaultAppLaunchManager: AppLaunchManager {
    let rootViewController: UIViewController
    //let dataPrivacyViewController: UIViewController
    init() {
        rootViewController = DataPrivacyViewController()//RootViewController()
        //dataPrivacyViewController = DataPrivacyViewController()
    }
}

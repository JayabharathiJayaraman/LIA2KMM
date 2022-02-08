import UIKit

protocol AppLaunchManager {
    var rootViewController: UIViewController { get }
    //var dataPrivacyPolicyViewController: UIViewController { get }
}

struct DefaultAppLaunchManager: AppLaunchManager {
    let rootViewController: UIViewController
    //let dataPrivacyPolicyViewController: UIViewController
    init() {
        rootViewController = RootViewController()
        //dataPrivacyPolicyViewController = DataPrivacyPolicyViewController()
    }
}

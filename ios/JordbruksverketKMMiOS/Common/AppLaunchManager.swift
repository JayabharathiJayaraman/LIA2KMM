import UIKit

protocol AppLaunchManager {
    //var rootViewController: UIViewController { get }
    var  aboutViewController: UIViewController { get }
}

struct DefaultAppLaunchManager: AppLaunchManager {
    //let rootViewController: UIViewController
    let aboutViewController: UIViewController

    init() {
        //rootViewController = RootViewController()
        aboutViewController = AboutViewController()
    }
}

import UIKit

protocol AppLaunchManager {
    var rootViewController: UIViewController { get }
}

struct DefaultAppLaunchManager: AppLaunchManager {
    let rootViewController: UIViewController
   
    init() {
        rootViewController = RootViewController() 
    }
}

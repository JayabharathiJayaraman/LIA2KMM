import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    var appLaunchManager: AppLaunchManager = DefaultAppLaunchManager()

    func scene(_ scene: UIScene,
               willConnectTo session: UISceneSession,
               options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        window?.rootViewController = appLaunchManager.rootViewController
        //window?.rootViewController = appLaunchManager.aboutViewController
        window?.makeKeyAndVisible()
    }
}

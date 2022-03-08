import UIKit

extension UIColor {
    convenience init(named name: String) {
        #if DEBUG
        self.init(named: name)!
        #else
        self.init(named: name) ?? UIImage()
        #endif
    }
    
    struct Jordbruksverket {
        static let defaultTextColor = UIColor(named: "defaultText")
        static let defaultBackgroundColor = UIColor(named: "defaultBackground")
    }
}

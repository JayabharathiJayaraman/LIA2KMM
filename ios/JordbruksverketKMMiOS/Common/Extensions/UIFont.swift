import UIKit

extension UIFont {
    static func scaledFont(name: String, textStyle: UIFont.TextStyle) -> UIFont {
        let fontDescriptor = UIFontDescriptor.preferredFontDescriptor(withTextStyle: textStyle)

        guard let customFont = UIFont(name: name, size: fontDescriptor.pointSize) else {
            let systemFont = UIFont.systemFont(ofSize: fontDescriptor.pointSize)
            return UIFontMetrics.default.scaledFont(for: systemFont)
        }

        return UIFontMetrics.default.scaledFont(for: customFont)
    }
}

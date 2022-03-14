import UIKit

extension UIView {
    class func fromNib<T: UIView>(_ nibNameOrNil: String? = nil, bundle: Bundle = Bundle.main) -> T! {
        var view: T?
        let name: String
        if let nibName = nibNameOrNil {
            name = nibName
        } else {
            name = "\(T.self)".components(separatedBy: ".").last!
        }

        let nibViews = bundle.loadNibNamed(name, owner: nil, options: nil)
        for nibView in nibViews! {
            if let tog = nibView as? T {
                view = tog
            }
        }
        return view
    }

    @IBInspectable var cornerRadius: CGFloat {
        get {
            return layer.cornerRadius
        }
        set {
            layer.cornerRadius = newValue
            layer.masksToBounds = newValue > 0
        }
    }
}

public extension UIView {
    func initFromSelfNib() {
        let className = String(describing: type(of: self))
        customInit(fromNib: className)
    }
}

private extension UIView {
    func customInit(fromNib nibName: String) {
        if let contentView = Bundle.main.loadNibNamed(nibName, owner: self, options: nil)?.first as? UIView {
            contentView.frame = bounds
            addSubview(contentView)
        }
    }
}

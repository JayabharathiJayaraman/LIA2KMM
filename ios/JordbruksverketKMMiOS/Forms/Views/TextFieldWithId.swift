import UIKit

class TextFieldWithId: UITextField {
    var idString: String?
    var bottomBorder = UIView()
    
    override func draw(_ rect: CGRect) {
        super.draw(rect)
        
        addBottomBorder(withHeight: 1)
    }
    
    override func textRect(forBounds bounds: CGRect) -> CGRect {
        return bounds.inset(by: UIEdgeInsets(top: 14, left: 0, bottom: 10, right: 0))
    }
    
    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        return bounds.inset(by: UIEdgeInsets(top: 14, left: 0, bottom: 10, right: 0))
    }
    
    private func addBottomBorder(withHeight height: CGFloat) {
        self.translatesAutoresizingMaskIntoConstraints = false
        bottomBorder = UIView.init(frame: CGRect(x: 0, y: 0, width: 0, height: 0))
        bottomBorder.backgroundColor = UIColor.black.withAlphaComponent(0.3)
        
        addSubview(bottomBorder)

        bottomBorder.translatesAutoresizingMaskIntoConstraints = false
        bottomBorder.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
        bottomBorder.leftAnchor.constraint(equalTo: leftAnchor).isActive = true
        bottomBorder.rightAnchor.constraint(equalTo: rightAnchor).isActive = true
        bottomBorder.heightAnchor.constraint(equalToConstant: height).isActive = true
    }
}

import UIKit

final class DynamicLabel: UILabel {
    var idString: String?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        setAutoUpdate()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        setAutoUpdate()
    }
    
    private func setAutoUpdate() {
        self.adjustsFontForContentSizeCategory = true
    }
}

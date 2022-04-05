import Foundation
import UIKit

class ChecklistWithId : UIButton{
    var idString: String?
    var buttonList : [UIButton]!
    
    override init(frame: CGRect){
        super.init(frame: frame)
        
    }
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

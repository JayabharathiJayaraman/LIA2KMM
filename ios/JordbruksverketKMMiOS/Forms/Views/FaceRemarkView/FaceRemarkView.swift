import UIKit

typealias FaceRemarkCompletion = (() -> Void)

class FaceRemarkView: UIView {
    
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var label: UILabel!
    var completionHandler: FaceRemarkCompletion?
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var button: UIButton!
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initFromSelfNib()
    }
    
    required init?(coder: NSCoder) {
        return nil
    }

    func configure(image:UIImage?, text: String, completionHandler:@escaping FaceRemarkCompletion){
        imageView?.image = image
        label.text = text
        label.textColor =  UIColor.Jordbruksverket.defaultTextColor
        label.adjustsFontForContentSizeCategory = true
        self.completionHandler = completionHandler
    }
    
    func configureResult(image:UIImage?, text: String, color:String){
        imageView?.image = image
        imageView.layer.cornerRadius = 40
        if(color == "red_round_background")
        {
            imageView.backgroundColor =  UIColor.Jordbruksverket.redRoundBackGround
        }
        if(color == "orange_round_background")
        {
            imageView.backgroundColor =  UIColor.Jordbruksverket.orangeRoundBackGround
        }
        if(color == "green_round_background")
        {
            imageView.backgroundColor =  UIColor.Jordbruksverket.greenRoundBackGround
        }
        
        label.text = text
        label.textColor =  UIColor.Jordbruksverket.defaultTextColor
        label.adjustsFontForContentSizeCategory = true
        contentView.backgroundColor = UIColor.Jordbruksverket.defaultBackgroundColor
    }
    
    @IBAction func buttonTapped(_ sender: Any) {
        completionHandler?()
    }
}


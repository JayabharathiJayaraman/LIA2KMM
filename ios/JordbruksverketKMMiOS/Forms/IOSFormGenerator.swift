import Foundation
import shared
import UIKit
import MapKit

class IOSFormGenerator: FormGenerator {
    private var mainView = UIStackView()
    private var currentScreenRendered: Int32 = 0
    
    var presentingViewController: UIViewController?
    
    init() {
        mainView.axis = .vertical
        mainView.distribution = .fillProportionally
    }
    
    func updateInterface(components: [FormComponent], currentScreen: Int32) {
        if currentScreen != currentScreenRendered {
            presentingViewController?.children.forEach { child in
                child.willMove(toParent: nil)
                child.removeFromParent()
            }
            mainView.subviews.forEach { $0.removeFromSuperview() }
            currentScreenRendered = currentScreen
        }
        
        generateInterface(components: components, currentScreen: currentScreen.asKotlinInt)
    }
    
    func createInterface(components: [FormComponent], currentScreen: Int32) -> Any {
        generateInterface(components: components, currentScreen: currentScreen.asKotlinInt)
        
        return mainView
    }
    
    func generateInterface(components: [FormComponent], currentScreen: KotlinInt?) {
        guard let currentScreen = currentScreen else { return }
        let screenTag = Int(truncating: currentScreen)
        
        for component in components {
            switch component.type {
            case .body:
                if let body = component as? FormComponentText {
                    mainView.createBodyLabel(id: body.id, text: body.text)
                }
            case .titlebig:
                if let title = component as? FormComponentText {
                    mainView.addBigTitleLabel(id: title.id, text: title.text)
                }
            case .titlesmall:
                if let title = component as? FormComponentText {
                    mainView.addSmallTitleLabel(id: title.id, text: title.text)
                }
            case .textfield:
                if let textfield = component as? FormComponentTextField {
                    mainView.createOrUpdateTextField(id: textfield.id, text: textfield.text, placeholder: textfield.placeholder)
                }
            case .buttonlist:
                if let buttonlist = component as? FormComponentButtonList {
                    addButtonList(id: buttonlist.id, title: buttonlist.title, list: buttonlist.list, value: buttonlist.value, placeholder: buttonlist.placeholder)
                }
            case .button:
                if let button = component as? FormComponentButton {
                    mainView.addButton(id: button.id, text: button.text)
                }
            case .image:
                if let image = component as? FormComponentImage {
                    mainView.addImage(id: image.id, imageName: image.image, caption: image.caption)
                }
            case .information:
                if let information = component as? FormComponentInformation {
                    addInformationView(id: information.id, components: information.components)
                }
            case .video:
                if let video = component as? FormComponentVideo {
                    addVideo(source: video.source)
                }
            case .imagesgrid:
                if let captionedImages = component as? FormComponentImagesGrid{
                    mainView.addCaptionedImages(screenTag: screenTag, id: captionedImages.id, imageNames: captionedImages.image, captions: captionedImages.caption)
                }
            case .resultsimages:
                if let resultsImages = component as? FormComponentResultsImages{
                    mainView.addCaptionedImages(screenTag: screenTag, id: resultsImages.id,imageNames: resultsImages.images,captions: resultsImages.imagesTextList)
                }
            case .checklist:
                if let checkList = component as? FormComponentChecklist{
                    mainView.createOrUpdateChecklist(screenTag: screenTag, id: checkList.id, title : checkList.title, options: checkList.options, rating: Int(checkList.rating))
                }
            case .maps:
                if let mapImage = component as? FormComponentMap {
                    addMap(screenTag: screenTag, id: mapImage.id)
                }
            case .textfieldnotes:
                if let textfieldNotes = component as? FormComponentTextField {
                    mainView.addTextFieldNotes(id: textfieldNotes.id, text: textfieldNotes.text, placeholder: textfieldNotes.placeholder)
                }
            case .resultsremarksface:
                if let resultRemarks = component as? FormComponentResultsRemark {
                    mainView.addResultRemarks(screenTag:screenTag, text: resultRemarks.text, id: resultRemarks.id, image: resultRemarks.image, color: resultRemarks.color)
                }
            case .resultsimages:
                if let resultsImages = component as? FormComponentResultsImages{
                    mainView.addCaptionedImages(screenTag: screenTag, id: resultsImages.id,imageNames: resultsImages.images,captions: resultsImages.imagesTextList)
                }
            case .questionnaire:
                if let questionnaire = component as? FormComponentQuestionnaire {
                    mainView.addQuestionnaire(id: questionnaire.id, text: questionnaire.text, answer: questionnaire.answer)
                }
            case .questionnaireresult:
                if let questionnaireResult = component as? FormComponentQuestionnaireResult {
                    mainView.addQuestionnaireResult(id: questionnaireResult.id, answers: questionnaireResult.answers as! [AnswerWithPhoto])
                }
            default:
                print("unknown component")
            }
        }
        mainView.layoutSubviews()
        print("Subviews: \(mainView.subviews)")
    }
}

private extension IOSFormGenerator {
    func addButtonList(
        id: String,
        title: String,
        list: [String],
        value: String,
        placeholder: String
    ) {
        mainView.addSmallTitleLabel(id: id, text: title)
        
        let verticalSpacing = mainView.getVerticalSpacingView(withHeight: 5)
        mainView.addArrangedSubview(verticalSpacing)
        
        let button = ButtonWithList()
        button.id = id
        button.list = list
        
        button.setTitle(placeholder, for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.contentHorizontalAlignment = .left
        button.backgroundColor = .white
        button.contentEdgeInsets = UIEdgeInsets(top: 11, left: 12, bottom: 11, right: 12)
        button.layer.cornerRadius = 4
        button.layer.shadowColor = UIColor.black.cgColor
        button.layer.shadowRadius = 5
        button.layer.shadowOpacity = 0.5
        button.layer.shadowOffset = CGSize(width: 0, height: 3)
        button.titleLabel?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
        button.titleLabel?.adjustsFontForContentSizeCategory = true
        button.addTarget(self, action: #selector(handleButtonListButtonTap), for: .touchUpInside)
        
        mainView.addArrangedSubview(button)
    }
    
    @objc
    func handleButtonListButtonTap(_ button: ButtonWithList) {
        guard
            let id = button.id,
            let list = button.list,
            let presentingViewController = presentingViewController
        else { return }
        
        let buttonListViewController = ButtonListViewController(list: list)
        let modalViewController = ModalViewController(contentViewController: buttonListViewController)
        
        buttonListViewController.itemSelectionHandler = { [weak modalViewController] item in
            button.setTitle(item, for: .normal)
            modalViewController?.dismiss()
        }
        
        modalViewController.present(using: presentingViewController)
    }
    
    func addInformationView(id: String, components: [FormComponent]) {
        if mainView.subviews.first(where: { view in (view as? StackViewWithId)?.idString == id }) == nil {
            let button = ButtonWithComponents()
            button.components = components
            button.setImage(UIImage(named: "infoIcon"), for: .normal)
            button.setTitleColor(.black, for: .normal)
            button.addTarget(self, action: #selector(handleInformationButtonTap), for: .touchUpInside)
            
            button.translatesAutoresizingMaskIntoConstraints = false
            button.widthAnchor.constraint(equalToConstant: 53.0).isActive = true
            button.heightAnchor.constraint(equalToConstant: 50.0).isActive = true
            
            let stackView = StackViewWithId(arrangedSubviews: [UIView(), button])
            stackView.idString = id
            stackView.axis = .horizontal
            
            mainView.addArrangedSubview(stackView)
        }
    }
    
    @objc
    func handleInformationButtonTap(_ button: ButtonWithComponents) {
        guard
            let components = button.components,
            let presentingViewController = presentingViewController
        else { return }
        
        let informationViewController = InformationViewController(components: components)
        let modalViewController = ModalViewController(contentViewController: informationViewController)
        modalViewController.present(using: presentingViewController)
    }
    
    func addVideo(source: String) {
        guard let url = Bundle.main.url(forResource: source, withExtension: "mp4") else { return }
        
        /*        let player = AVPlayer(url: url)
         let playerViewController = AVPlayerViewController()
         playerViewController.player = player
         playerViewController.videoGravity = .resizeAspectFill
         
         playerViewController.view.translatesAutoresizingMaskIntoConstraints = false
         playerViewController.view.heightAnchor.constraint(equalTo: playerViewController.view.widthAnchor).isActive = true
         
         mainView.addArrangedSubview(playerViewController.view)
         presentingViewController?.addChild(playerViewController)
         playerViewController.didMove(toParent: presentingViewController) */
    }
    
    func addMap(screenTag: Int, id: String) {
        let mapViewController = MapScreenViewController()
        
        mainView.addArrangedSubview(mapViewController.view)
        presentingViewController?.addChild(mapViewController)
        mapViewController.didMove(toParent: presentingViewController)
        
    }
}

extension UIStackView {
    func addImage(id: String, imageName: String, caption: String) {
        var verticalSpace = getVerticalSpacingView(withHeight: 20)
        self.addArrangedSubview(verticalSpace)
        
        let imageView = UIImageView()
        let stackView = UIStackView()
        self.addArrangedSubview(stackView)
        
        imageView.image = UIImage(named: imageName)
        imageView.widthAnchor.constraint(equalToConstant: 250).isActive = true
        imageView.heightAnchor.constraint(equalToConstant: 250).isActive = true
        imageView.contentMode = .scaleAspectFit
        let label = getDefaultLabel(id: id)
        label.text = caption
        label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .callout)
        label.textAlignment = .left
        
        stackView.addArrangedSubview(imageView)
        verticalSpace = getVerticalSpacingView(withHeight: 10)
        self.addArrangedSubview(verticalSpace)
        self.addArrangedSubview(label)
    }
    
    func addBigTitleLabel(id: String, text: String) {
        if self.subviews.first(where: { view in (view as? DynamicLabel)?.idString == id }) == nil {
            let label = getDefaultLabel(id: id)
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .title2)
            label.textColor =  UIColor.Jordbruksverket.labelTitleColor
            
            self.addArrangedSubview(label)
        }
    }
    
    func addSmallTitleLabel(id: String, text: String) {
        if self.subviews.first(where: { view in (view as? DynamicLabel)?.idString == id }) == nil {
            let verticalSpacing = getVerticalSpacingView(withHeight: 20)
            self.addArrangedSubview(verticalSpacing)
            
            let label = getDefaultLabel(id: id)
            label.text = text
            label.textColor =  UIColor.Jordbruksverket.labelTitleColor
            label.font = UIFont.scaledFont(name: UIFont.fontNameBold, textStyle: .body)
            
            self.addArrangedSubview(label)
        }
    }
    
    func createBodyLabel(id: String, text: String) {
        print("createBodyLabel before IF")
        if self.subviews.first(where: { view in (view as? DynamicLabel)?.idString == id}) == nil {
            print("createBodyLabel after IF")
            let verticalSpacing = getVerticalSpacingView(withHeight: 10)
            self.addArrangedSubview(verticalSpacing)
            print("bodyTitle \(id)")
            
            let label = getDefaultLabel(id: id)
            label.text = text
            label.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            
            self.addArrangedSubview(label)
        }
    }
    
    func createOrUpdateTextField(id: String, text: String, placeholder: String) {
        if let existingView = (self.subviews.first(where: { view in
            (view as? TextFieldWithId)?.idString == id
        }) as? TextFieldWithId) {
            existingView.text = text
        } else {
            let verticalSpacing = getVerticalSpacingView(withHeight: 10)
            self.addArrangedSubview(verticalSpacing)
            
            let textField = TextFieldWithId()
            textField.text = text
            textField.placeholder = placeholder
            textField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            textField.textColor = UIColor.Jordbruksverket.defaultTextColor
            textField.idString = id
            textField.addTarget(self, action: #selector(textFieldChange), for: .editingChanged)
            
            self.addArrangedSubview(textField)
        }
    }
    
    @objc
    func textFieldChange(_ sender: TextFieldWithId) {
        guard let id = sender.idString else { return }
        IOSFormViewModel.shared.formViewModel.setTextData(id: id, text: sender.text ?? "")
    }
    
    func addButton(id: String, text: String){
        if self.subviews.first(where: { view in (view as? ButtonWithId)?.idString == id }) == nil {
            let verticalSpacing = getVerticalSpacingView(withHeight: 30)
            self.addArrangedSubview(verticalSpacing)
            
            let button = ButtonWithId()
            button.idString = id
            let widthConstraint = button.widthAnchor.constraint(equalToConstant: 30.0)
            let heightConstraint = button.heightAnchor.constraint(equalToConstant: 30.0)
            NSLayoutConstraint.activate([widthConstraint, heightConstraint])
            heightConstraint.constant = 80
            button.setTitle(text, for: .normal)
            button.titleLabel!.lineBreakMode = .byWordWrapping
            button.setTitleColor(UIColor.Jordbruksverket.buttonTitleColor, for: .normal)
            button.contentHorizontalAlignment = .center
            button.backgroundColor = .white
            button.titleLabel?.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            button.titleLabel?.adjustsFontForContentSizeCategory = true
            
            self.addArrangedSubview(button)
        }
    }
    
    func addQuestionnaire(id: String, text: [String],answer: QuestionnaireAnswer?)
    {
        if self.subviews.first(where: { view in (view as? StackViewWithId)?.idString == id }) == nil {
            let verticalSpace = getVerticalSpacingView(withHeight: 30)
            self.addArrangedSubview(verticalSpace)
            
            if (answer == nil){
                setQuestionnaireAnswered(isAnswered: false)
            }
            
            let label = QuestionnaireAnswerLabelWithId()
            label.idString = id
            
            let faceRemarkViewSad = FaceRemarkView()
            let faceRemarkViewIndifferent = FaceRemarkView()
            let faceRemarkViewHappy = FaceRemarkView()
            
            let stackView = StackViewWithId()
            stackView.idString = id
            stackView.axis = .vertical
            stackView.spacing = 10
            if (answer == QuestionnaireAnswer.poor) {
                faceRemarkViewSad.contentView.backgroundColor = UIColor.Jordbruksverket.redRoundBackGround
            }
            else if (answer == QuestionnaireAnswer.mediocre){
                faceRemarkViewIndifferent.contentView.backgroundColor = UIColor.Jordbruksverket.orangeRoundBackGround
            }
            else if (answer == QuestionnaireAnswer.good) {
                faceRemarkViewHappy.contentView.backgroundColor = UIColor.Jordbruksverket.greenRoundBackGround
            }
            else {
                faceRemarkViewHappy.contentView.backgroundColor = .white
            }
            
            faceRemarkViewSad.configure(image: UIImage(named: "sad_face"), text: text[0]){ [self] in
                label.text = text[0]
                handleQuestionnaireAnswersSad(label)
                faceRemarkViewSad.contentView.backgroundColor = UIColor.Jordbruksverket.redRoundBackGround
                faceRemarkViewIndifferent.contentView.backgroundColor = .white
                faceRemarkViewHappy.contentView.backgroundColor = .white
            }
            
            faceRemarkViewIndifferent.configure(image: UIImage(named: "indifferent_face"), text: text[1]){ [self] in
                label.text = text[1]
                handleQuestionnaireAnswersIndifferent(label)
                faceRemarkViewIndifferent.contentView.backgroundColor = UIColor.Jordbruksverket.orangeRoundBackGround
                faceRemarkViewSad.contentView.backgroundColor = .white
                faceRemarkViewHappy.contentView.backgroundColor = .white
            }
            
            faceRemarkViewHappy.configure(image: UIImage(named: "happy_face"), text: text[2]){ [self] in
                label.text = text[2]
                handleQuestionnaireAnswersHappy(label)
                faceRemarkViewHappy.contentView.backgroundColor = UIColor.Jordbruksverket.greenRoundBackGround
                faceRemarkViewSad.contentView.backgroundColor = .white
                faceRemarkViewIndifferent.contentView.backgroundColor = .white
            }
            let verticalSpaceSad = getVerticalSpacingView(withHeight: 15)
            let verticalSpaceAnswer = getVerticalSpacingView(withHeight: 15)
            
            stackView.addArrangedSubview(faceRemarkViewSad)
            stackView.addArrangedSubview(faceRemarkViewIndifferent)
            stackView.addArrangedSubview(faceRemarkViewHappy)
            self.addArrangedSubview(stackView)
        }
    }

    func handleQuestionnaireAnswersSad(_ sender: QuestionnaireAnswerLabelWithId){
        guard let id = sender.idString else { return }
        guard let text = sender.text else { return }
        let faceRemarkViewSad = FaceRemarkView()
        IOSFormViewModel.shared.formViewModel.setQuestionnaireAnswer(id: id, answer: QuestionnaireAnswer.poor, text: text)
        setQuestionnaireAnswered(isAnswered: true)
    }
    
    func handleQuestionnaireAnswersIndifferent(_ sender: QuestionnaireAnswerLabelWithId){
        guard let id = sender.idString else { return }
        guard let text = sender.text else { return }
        IOSFormViewModel.shared.formViewModel.setQuestionnaireAnswer(id: id, answer: QuestionnaireAnswer.mediocre, text: text)
        setQuestionnaireAnswered(isAnswered: true)
    }
    
    func handleQuestionnaireAnswersHappy(_ sender: QuestionnaireAnswerLabelWithId){
        guard let id = sender.idString else { return }
        guard let text = sender.text else { return }
        IOSFormViewModel.shared.formViewModel.setQuestionnaireAnswer(id: id, answer: QuestionnaireAnswer.good, text: text)
        setQuestionnaireAnswered(isAnswered: true)
    }
    
    func setQuestionnaireAnswered(isAnswered: KotlinBoolean) {
        IOSFormViewModel.shared.formViewModel.form.data.questionnaireIsAnswered.answered = isAnswered
    }
    
    func addQuestionnaireResult(id: String, answers: [AnswerWithPhoto]){
        let answersCount = answers.count
        for i in 0...answersCount-1{
            let verticalSpaceView = getVerticalSpacingView(withHeight: 15)
            self.addArrangedSubview(verticalSpaceView)
            let selectedAnswer = answers[i].answer!
            let faceRemarkView = FaceRemarkView()
            self.addArrangedSubview(faceRemarkView)
            switch selectedAnswer {
            case QuestionnaireAnswer.poor:
                faceRemarkView.configureResult(image: UIImage(named: "sad_face"), text: answers[i].text, color: "red_round_background")
                faceRemarkView.contentView.backgroundColor = UIColor.Jordbruksverket.redRoundBackGround
            case QuestionnaireAnswer.mediocre:
                faceRemarkView.configureResult(image: UIImage(named: "indifferent_face"), text: answers[i].text, color: "orange_round_background")
                faceRemarkView.contentView.backgroundColor = UIColor.Jordbruksverket.orangeRoundBackGround
            case QuestionnaireAnswer.good:
                faceRemarkView.configureResult(image: UIImage(named: "happy_face"), text: answers[i].text, color: "green_round_background")
                faceRemarkView.contentView.backgroundColor = UIColor.Jordbruksverket.greenRoundBackGround
            default:
                print("No Answer Found")
            }
        }
    }
    
    func addTextFieldNotes(id: String, text: String, placeholder: String) {
        if let existingView = (self.subviews.first(where: { view in
            (view as? TextFieldWithId)?.idString == id
        }) as? TextFieldWithId) {
            existingView.text = text
        } else {
            let verticalSpacing = getVerticalSpacingView(withHeight: 30)
            self.addArrangedSubview(verticalSpacing)
            
            let textField = TextFieldWithId()
            let stackView = UIStackView()
            self.addArrangedSubview(stackView)
            textField.placeholder = placeholder
            textField.backgroundColor = .white
            textField.contentVerticalAlignment = UIControl.ContentVerticalAlignment.top
            textField.font = UIFont.scaledFont(name: UIFont.fontNameRegular, textStyle: .body)
            textField.textColor = UIColor.Jordbruksverket.defaultTextColor
            textField.heightAnchor.constraint(equalToConstant: 300).isActive = true
            
            stackView.addArrangedSubview(textField)
        }
    }
    
    func addResultRemarks(screenTag: Int, text: String, id: String, image: String,color:String) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            let verticalSpace = getVerticalSpacingView(withHeight: 10)
            self.addArrangedSubview(verticalSpace)
            
            let faceRemarkView = FaceRemarkView()
            faceRemarkView.configureResult(image: UIImage(named: image), text: text, color: color)
            
            self.addArrangedSubview(faceRemarkView)
        }
    }
    
    func addCaptionedImages(screenTag: Int, id: String, imageNames: [String], captions: [String]) {
        if (id == "resultsImages") {
            var resultsCaptionedImages = [UIStackView]()
            for i in 0...2{
                resultsCaptionedImages += [addResultsImages(id: id,names: imageNames[i],captions:captions[i])]
            }
            addHorizontalGridView(captionedImages: resultsCaptionedImages)
        }
        
        if (id == "toolsImages") {
            if imageNames.count == 3 {
                var captionedImages = [UIStackView]()
                for i in 0...2{
                    captionedImages += [addGridImages(id: id,names: imageNames[i],captions:captions[i])]
                }
                addHorizontalGridView(captionedImages: captionedImages)
            } else if imageNames.count > 3 {
                var captionedImagesRow1 = [UIStackView]()
                for i in 0...2 {
                    captionedImagesRow1 += [addGridImages(id: id,names: imageNames[i],captions:captions[i])]
                }
                addHorizontalGridView(captionedImages: captionedImagesRow1)
                
                var captionedImagesRow2 = [UIStackView]()
                for i in 3...4 {
                    captionedImagesRow2 += [addGridImages(id: id,names: imageNames[i],captions:captions[i])]
                }
                addHorizontalGridView(captionedImages: captionedImagesRow2)
                
                let verticalSpacing = getVerticalSpacingView(withHeight: 3)
                self.addArrangedSubview(verticalSpacing)
            }
        }
    }
    
    func addHorizontalGridView(captionedImages : [UIStackView]){
        let horizontalImageView = UIStackView(arrangedSubviews: captionedImages)
        horizontalImageView.axis = .horizontal
        horizontalImageView.distribution = .fillEqually
        horizontalImageView.alignment = .fill
        horizontalImageView.spacing = 10
        horizontalImageView.translatesAutoresizingMaskIntoConstraints = false
        self.addArrangedSubview(horizontalImageView)
    }
    
    func addGridImages(id: String,names:String,captions:String) -> UIStackView{
        let imageStackView = UIStackView()
        imageStackView.axis = .vertical
        imageStackView.distribution = .fillEqually
        imageStackView.alignment = .center
        imageStackView.spacing = 5
        imageStackView.translatesAutoresizingMaskIntoConstraints = false
        
        let imageView = UIImageView()
        imageView.image = UIImage(named: names)
        imageView.contentMode = .scaleAspectFit
        imageView.frame.size = CGSize(width: 10, height: 10)
        
        let captionStackView = UIStackView()
        captionStackView.axis = .vertical
        captionStackView.distribution = .fillEqually
        captionStackView.alignment = .center
        captionStackView.spacing = 5
        captionStackView.translatesAutoresizingMaskIntoConstraints = false
        
        let label = UILabel()
        label.text = captions
        captionStackView.addArrangedSubview(label)
        
        let verticalSpace = getVerticalSpacingView(withHeight: 50)
        
        imageStackView.addArrangedSubview(verticalSpace)
        imageStackView.addArrangedSubview(imageView)
        imageStackView.addArrangedSubview(captionStackView)
        
        return imageStackView
    }
    
    func addResultsImages(id: String,names:String,captions:String)-> UIStackView{
        print("ID inside addResultsImages:", id)
        let imageStackView = UIStackView()
        imageStackView.axis = .vertical
        imageStackView.distribution = .fillEqually
        imageStackView.translatesAutoresizingMaskIntoConstraints = false
        
        let imageView = UIImageView()
        imageView.image = UIImage(named: names)
        imageView.contentMode = .scaleAspectFit
        imageView.frame.size = CGSize(width: 30, height: 30)
        imageView.backgroundColor = .white
        imageView.layer.cornerRadius = 30
        imageView.clipsToBounds = true
        
        let captionStackView = UIStackView()
        captionStackView.axis = .vertical
        captionStackView.distribution = .fillEqually
        captionStackView.alignment = .center
        captionStackView.spacing = 5
        captionStackView.translatesAutoresizingMaskIntoConstraints = false
        
        let label = UILabel()
        label.text = captions
        label.lineBreakMode = .byWordWrapping
        label.numberOfLines = 2
        label.textAlignment = .center
        captionStackView.addArrangedSubview(label)
        
        let verticalSpace = getVerticalSpacingView(withHeight: 50)
        
        imageStackView.addArrangedSubview(verticalSpace)
        imageStackView.addArrangedSubview(imageView)
        imageStackView.addArrangedSubview(captionStackView)
        
        return imageStackView
    }
    
    func createOrUpdateChecklist(screenTag: Int, id: String, title: String, options: [String], rating: Int) {
        if self.subviews.first(where: { view in view.tag == screenTag }) == nil {
            var i : Int = 0
            
            let verticalChecklist = UIStackView()
            verticalChecklist.axis = .vertical
            verticalChecklist.distribution = .fillEqually
            verticalChecklist.alignment = .fill
            verticalChecklist.translatesAutoresizingMaskIntoConstraints = false
            self.addArrangedSubview(verticalChecklist)
            
            var radioButtonsGroup = [UIButton]()
            for option in options
            {
                let radioView = UIStackView()
                radioView.axis = .horizontal
                self.addArrangedSubview(radioView)
                radioView.spacing = 5
                radioView.translatesAutoresizingMaskIntoConstraints = false
                radioView.heightAnchor.constraint(equalToConstant: 50).isActive = true
                
                let radioButton = ChecklistWithId()
                radioView.addArrangedSubview(radioButton)
                radioButton.translatesAutoresizingMaskIntoConstraints = false
                radioButton.topAnchor.constraint(equalTo: radioView.topAnchor).isActive = true
                radioButton.bottomAnchor.constraint(equalTo: radioView.bottomAnchor).isActive = true
                radioButton.tag = i
                radioButton.idString = id
                radioButton.setImage(UIImage(named: "switch_unchecked_green.png"), for: .normal)
                radioButton.addTarget(self, action: #selector(selectedCheckList(_:)), for: .touchUpInside)
                
                radioButtonsGroup.append(radioButton)
                i += 1
                radioButton.buttonList = radioButtonsGroup
                
                for i in radioButtonsGroup{
                    if i.tag == rating{
                        i.isSelected = true
                        i.setImage(UIImage(named: "switch_checked.png"), for: .selected)
                    }
                    else{
                        i.isSelected = false
                        i.setImage(UIImage(named: "switch_unchecked_green.png"), for: .normal)
                    }
                }
                
                let radioLabel = UILabel()
                radioView.addSubview(radioLabel)
                radioLabel.translatesAutoresizingMaskIntoConstraints = false
                radioLabel.font = UIFont(name: UIFont.fontNameRegular, size: 20.0)
                radioLabel.centerYAnchor.constraint(equalTo: radioView.centerYAnchor).isActive = true
                radioLabel.leftAnchor.constraint(equalTo: radioButton.rightAnchor, constant: 1).isActive = true
                radioLabel.textColor = UIColor.Jordbruksverket.defaultTextColor
                radioLabel.text = option
                
                verticalChecklist.addArrangedSubview(radioView)
                
                let verticalSpace = getVerticalSpacingView(withHeight: 3)
                radioView.addArrangedSubview(verticalSpace)
            }
            print("radio button list group count:",radioButtonsGroup.count)
        }
    }
    
    @objc func selectedCheckList(_ sender: ChecklistWithId){
        let selectedButton = sender
        
        print("Inside selected radio button list count ", selectedButton.buttonList.count)
        print("selected button tag:",selectedButton.tag)
        
        for i in 0...selectedButton.buttonList.count-1{
            print("Inside Selected " , selectedButton.buttonList[i].tag, selectedButton.tag )
            if (selectedButton.buttonList[i].tag == selectedButton.tag){
                selectedButton.buttonList[i].isSelected = !selectedButton.buttonList[i].isSelected
                selectedButton.buttonList[i].setImage(UIImage(named: "switch_checked.png"), for: .selected)
            }
            else{
                selectedButton.buttonList[i].isSelected = false
                selectedButton.setImage(UIImage(named: "switch_unchecked_green.png"), for: .selected)
            }
        }
        guard let id = sender.idString else { return }
        IOSFormViewModel.shared.formViewModel.setChecklistRating(id: id, rating:  Int32(selectedButton.tag))
    }
    
    func getDefaultLabel(id: String) -> UILabel {
        let label = DynamicLabel()
        label.idString = id
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.adjustsFontForContentSizeCategory = true
        label.textColor = UIColor.Jordbruksverket.defaultTextColor
        
        return label
    }
    
    func getVerticalSpacingView(withHeight height: CGFloat) -> UIView {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.heightAnchor.constraint(equalToConstant: height).isActive = true
        
        return view
    }
}

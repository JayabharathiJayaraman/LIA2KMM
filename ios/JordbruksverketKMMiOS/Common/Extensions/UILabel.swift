import UIKit

extension UILabel {

  func dynamicFontBold(){
    guard let customFont = UIFont(name: "opensans-bold", size: UIFont.labelFontSize) else {
      fatalError("""
        Failed to load the "opensans-bold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true
  }

  func dynamicFontSemiBoldWelcome(){
    guard let customFont = UIFont(name: "opensans-semibold", size: 16) else {
      fatalError("""
        Failed to load the "opensans-semibold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true
  }

  func dynamicFontSemiBold(){
      guard let customFont = UIFont(name: "opensans-semibold", size: 14) else {
      fatalError("""
        Failed to load the "opensans-semibold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true
  }

  func dynamicFontSemiBoldAbout(){
      guard let customFont = UIFont(name: "opensans-semibold", size: 16.5) else {
      fatalError("""
        Failed to load the "opensans-semibold" font.
        Make sure the font file is included in the project and the font name is spelled correctly.
        """
      )
    }
    self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
    self.adjustsFontForContentSizeCategory = true
  }

    func dynamicFontRegular(){
      guard let customFont = UIFont(name: "opensans-regular", size: UIFont.labelFontSize) else {
        fatalError("""
          Failed to load the "opensans-regular" font.
          Make sure the font file is included in the project and the font name is spelled correctly.
          """
        )
      }
      self.font = UIFontMetrics(forTextStyle: .headline).scaledFont(for: customFont)
      self.adjustsFontForContentSizeCategory = true
    }
}
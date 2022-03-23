import UIKit
import MapKit

final class MapScreenViewController: UIViewController, CLLocationManagerDelegate {
    
  //  @IBOutlet private weak var titleLabel: UILabel!
   // @IBOutlet private weak var descriptionLabel: UILabel!
      @IBOutlet private weak var mapView: MKMapView!
  //  @IBOutlet private weak var infoView: UIView!
   // @IBOutlet private weak var infoViewLabel: UILabel!
   // @IBOutlet private weak var infoViewBackground: UIView!
   // @IBOutlet private weak var navigationTitleView: UIView!
  //  @IBOutlet private weak var infoButton: UIButton!
  //  @IBOutlet private weak var closeInfoButton: UIButton!
   // @IBOutlet private weak var txtLat: UITextField!
  //  @IBOutlet private weak var txtLng: UITextField!
    
    let locationManger = CLLocationManager()
    let regionInMeters: Double = 105
    
    override func viewDidLoad() {
        super.viewDidLoad()
        checkLocationServices()
        mapView.mapType = .satellite
    //    initView()
    }
    
  /*  @IBAction private func infoButtonPressed(_ sender: Any) {
        UIView.animate(withDuration: 0.4, animations: {
            self.infoView.alpha = 1
        })
        
        if (infoView.isHidden == true) {
            infoView.isHidden = false
        } else {
            infoView.isHidden = true
        }
        if (infoViewBackground.isHidden == true) {
            infoViewBackground.isHidden = false
        } else {
            infoViewBackground.isHidden = true
        }
        if (navigationTitleView.isHidden == true) {
            navigationTitleView.isHidden = false
        } else {
            navigationTitleView.isHidden = true
        }
    }
   
   */
    
 /*   @IBAction private func closeInfoButtonPressed(_ sender: Any) {
        UIView.animate(withDuration: 1, animations: {
            self.infoView.alpha = 0
        })
        
        if (infoView.isHidden == true) {
            infoView.isHidden = false
        } else {
            infoView.isHidden = true
        }
        if (infoViewBackground.isHidden == true) {
            infoViewBackground.isHidden = false
        } else {
            infoViewBackground.isHidden = true
        }
        if (navigationTitleView.isHidden == true) {
            navigationTitleView.isHidden = false
        } else {
            navigationTitleView.isHidden = true
        }
    }
  */
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return .portrait
    }
    
    private func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        guard !annotation.isKind(of: MKUserLocation.self) else { return nil }
        
        return MKAnnotationView()
    }
    
    private func setupLocationManger() {
        locationManger.delegate = self
        locationManger.desiredAccuracy = kCLLocationAccuracyBest
    }
    
    private func zoomInViewOnUserLocation () {
        if let location = locationManger.location?.coordinate {
            let region = MKCoordinateRegion.init(center: location, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
            mapView.setRegion(region, animated: true)
        }
    }
    
    private func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled(){
            setupLocationManger()
            checkLocationAuthorization()
            zoomInViewOnUserLocation()
        } else {
        }
    }
    
    private func checkLocationAuthorization() {
        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            mapView.showsUserLocation = true
            locationManger.delegate = self
            locationManger.startUpdatingLocation()
            break
        case .denied:
            break
        case .notDetermined:
            locationManger.requestWhenInUseAuthorization()
        case .restricted:
            break
        case .authorizedAlways:
            break
        }
    }
    
    internal func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        checkLocationServices()
    }
    
 /*   private func initView(){
        titleLabel.text = "TitleNameLabel".localized
        descriptionLabel.text = "DescriptionLabel".localized
        infoViewLabel.text = "InfoLabel".localized
        
    }
  */
    
    @IBAction private func addPin(_ sender: UITapGestureRecognizer) {
        let location = sender.location(in: self.mapView)
        let locCoord = self.mapView.convert(location, toCoordinateFrom: self.mapView)
        let lat:Double = locCoord.latitude
        let lng:Double = locCoord.longitude
        
    //    txtLat.text = ("  lat: \(lat)")
      //  txtLng.text = ("  lon: \(lng)")
        
        let annotation = MKPointAnnotation()
        
        annotation.coordinate = locCoord
        annotation.subtitle = "My mark"
        
        self.mapView.removeAnnotations(mapView.annotations)
        self.mapView.addAnnotation(annotation)
    }
}

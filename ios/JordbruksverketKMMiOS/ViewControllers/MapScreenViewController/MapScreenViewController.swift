import UIKit
import MapKit

class MapScreenViewController: UIViewController, CLLocationManagerDelegate {
    
    @IBOutlet weak var titleLabel: UILabel!
    
    @IBOutlet weak var descriptionLabel: UILabel!
    
    @IBOutlet weak var mapView: MKMapView!
        
    @IBOutlet weak var infoView: UIView!
    
    let locationManger = CLLocationManager()
    let regionInMeters: Double = 200
    
    override func viewDidLoad() {
        super.viewDidLoad()
        checkLocationServices()
        mapView.mapType = .satellite
        initView()
    }
    
    @IBOutlet weak var infoButton: UIButton!
    
    @IBAction func infoButtonPressed(_ sender: Any) {
        
        if (infoView.isHidden == true) {
            infoView.isHidden = false
        } else {
            infoView.isHidden = true
        }
    }
    

    @IBOutlet weak var closeInfoButton: UIButton!
    
    @IBAction func closeInfoButtonPressed(_ sender: Any) {
        
        if (infoView.isHidden == true) {
            infoView.isHidden = false
        } else {
            infoView.isHidden = true
        }
    }
    
    override var shouldAutorotate: Bool{
           return false
       }
       
       override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
           return .portrait
       }
       
       func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
           
           guard !annotation.isKind(of: MKUserLocation.self) else { return nil }
           
           
           return MKAnnotationView()
       }
       
       func setupLocationManger() {
           locationManger.delegate = self
           locationManger.desiredAccuracy = kCLLocationAccuracyBest
       }
       
       func zoomInViewOnUserLocation () {
           if let location = locationManger.location?.coordinate {
               let region = MKCoordinateRegion.init(center: location, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
               mapView.setRegion(region, animated: true)
       }
   }
       
       func checkLocationServices() {
           if CLLocationManager.locationServicesEnabled(){
               setupLocationManger()
               checkLocationAuthorization()
               zoomInViewOnUserLocation()
           } else {
           }
       }
       
       func checkLocationAuthorization() {
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
       
       func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
           checkLocationServices()
       }
       
       func initView(){
           
           titleLabel.text = "TitleNameLabel".localized
           descriptionLabel.text = "DescriptionLabel".localized
           
       }
       
       @IBOutlet weak var txtLat: UITextField!
       
       @IBOutlet weak var txtLng: UITextField!
       
       @IBAction func addPin(_ sender: UITapGestureRecognizer) {
           
           let location = sender.location(in: self.mapView)
           let locCoord = self.mapView.convert(location, toCoordinateFrom: self.mapView)
           let lat:Double = locCoord.latitude
           let lng:Double = locCoord.longitude
           
           txtLat.text = ("  lat: \(lat)")
           txtLng.text = ("  lon: \(lng)")
           
           let annotation = MKPointAnnotation()
           
           annotation.coordinate = locCoord
           annotation.subtitle = "My mark"
           
           self.mapView.removeAnnotations(mapView.annotations)
           self.mapView.addAnnotation(annotation)
   }
      }

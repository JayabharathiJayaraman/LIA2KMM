import UIKit
import MapKit

final class MapScreenViewController: UIViewController, CLLocationManagerDelegate {
    
      @IBOutlet private weak var mapView: MKMapView!
      @IBOutlet private weak var txtLat: UITextField!
      @IBOutlet private weak var txtLng: UITextField!
    
    let locationManger = CLLocationManager()
    let regionInMeters: Double = 105
    
    init() {
        super.init(nibName: String(describing: MapScreenViewController.self), bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        checkLocationServices()
        mapView.mapType = .satellite
        mapView.delegate = self
        
        let tapGestureRecognizer = UITapGestureRecognizer()
        tapGestureRecognizer.numberOfTapsRequired = 1
        tapGestureRecognizer.addTarget(self, action: #selector(addPinAnnotation(_:)))

        mapView.addGestureRecognizer(tapGestureRecognizer)
    }
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return .portrait
    }
    
    internal func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        guard annotation is MKPointAnnotation else { return nil }

            let identifier = "Annotation"
            var annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: identifier)

            if annotationView == nil {
                annotationView = MKPinAnnotationView(annotation: annotation, reuseIdentifier: identifier)
                annotationView!.canShowCallout = true
            } else {
                annotationView!.annotation = annotation
            }

            return annotationView
    }
    
    private func setupLocationManger() {
        locationManger.delegate = self
        locationManger.desiredAccuracy = kCLLocationAccuracyBest
    }
    
    private func zoomInViewOnUserLocation () {
        if IOSFormViewModel.shared.formViewModel.currentState.form.data.coordinates.latitude != nil {
            let latitude = IOSFormViewModel.shared.formViewModel.currentState.form.data.coordinates.latitude as! CLLocationDegrees
            let longitude = IOSFormViewModel.shared.formViewModel.currentState.form.data.coordinates.longitude  as! CLLocationDegrees
            let region = MKCoordinateRegion.init(center: CLLocationCoordinate2D(latitude: latitude, longitude: longitude), latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
            mapView.setRegion(region, animated: true)
        }else{
            if let location = locationManger.location?.coordinate {
                let region = MKCoordinateRegion.init(center: location, latitudinalMeters: regionInMeters, longitudinalMeters: regionInMeters)
                mapView.setRegion(region, animated: true)
            }
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
    
    @objc
    func addPinAnnotation(_ sender: UITapGestureRecognizer) {
       
        print("==> addPinAnnotation called...")
        let location = sender.location(in: self.mapView)
        let locCoord = self.mapView.convert(location, toCoordinateFrom: self.mapView)
        let lat:Double = locCoord.latitude
        let lng:Double = locCoord.longitude
        IOSFormViewModel.shared.formViewModel.setCoordinates(latitude: lat, longitude: lng)
          txtLat.text = ("  lat: \(lat)")
          txtLng.text = ("  lon: \(lng)")

        let annotation = MKPointAnnotation()
        
        annotation.coordinate = locCoord
        annotation.subtitle = "My mark"
        
        self.mapView.removeAnnotations(mapView.annotations)
        self.mapView.addAnnotation(annotation)
        
        print("lat: \(lat)")
        print("lat: \(lng)")
        
        print("==> \(mapView.annotations) ——— a.COunt: \(mapView.annotations.count)")
    }
}

extension MapScreenViewController: MKMapViewDelegate {
    func mapViewWillStartLoadingMap(_ mapView: MKMapView) {
        print(#function)
    }
    
    func mapViewDidChangeVisibleRegion(_ mapView: MKMapView) {
        
    }
}

export interface Accommodation {
    id: number;
    name: string;
    category: string;
    condition: string;
    hostId: number;
    hostFullName: string;
    numRooms: number;
    rented: boolean;
}